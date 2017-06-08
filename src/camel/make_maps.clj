(ns camel.make-maps
  (require [cheshire.core :refer :all])
  (require [clojure.string :as str])
  (require [clj-time.core :as t]))


(defn- month-map
  "Create the map for each futures month"
  [month-line]
  (let [name (first month-line)
        price (nth month-line 5)
        open-interest (last month-line)]
    {"name" name
     "price" price
     "open-interest" open-interest}))


(defn- future-map
  "Create the map for each future contract"
  [symbol exchange json-map expiration-map all-settlements]
  (let [future-delimiter (get-in json-map [exchange symbol "futures"])
        settles (first (str/split
                        (apply str (drop (str/index-of all-settlements future-delimiter)
                                         all-settlements)) #"TOTAL"))
        month-lines (rest (str/split settles #"\n"))
        month-lists (map #(str/split % #"\s+") month-lines)
        month-names (map first month-lists)
        month-data (map month-map month-lists)
        month-maps (zipmap month-names month-data)]
    {"futures" month-maps}))

  
(defn make-settlement-maps
  "Take the settlement text and create maps for easier access"
  ([exchanges]
   (make-settlement-maps exchanges {}))
  ([exchanges settlement-maps]
   (let [json-map (parse-string (slurp "resources\\commodities.json"))
         expiration-map (parse-string (slurp "resources\\expirations.json"))]
     (make-settlement-maps exchanges settlement-maps json-map expiration-map)))
  ([exchanges settlement-maps json-map expiration-map]
   (if (empty? exchanges)
     settlement-maps
     (let [exchange (first exchanges)
           commodities (keys (get json-map exchange))
           all-settlements (slurp (str "resources\\settlements\\" exchange ".txt"))
           futures (zipmap commodities
                           (map #(future-map % exchange json-map
                                             expiration-map all-settlements)
                                commodities))]
       (make-settlement-maps (rest exchanges) (merge settlement-maps futures)
                             json-map expiration-map)))))
