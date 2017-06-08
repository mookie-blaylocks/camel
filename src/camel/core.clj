
(ns camel.core
  (:require [[cheshire.core :refer :all]
            [clojure.string :as str]])
  (:gen-class))


(defn make-settlement-map
  "Take the settlement text and create maps for easier access"
  ([settlements]
   (make-settlement-map settlements '()))
  ([settlements settlement-maps]
   (let [json-map (parse-string (slurp "resources\\commodities.json"))]
     (make-settlement-map settlements settlement-maps json-map)))
  ([settlements settlement-maps json-map]
   (let [exchange (first (str/split (last (str/split (first settlements) #"\\")) #"[.]"))
         commodities (keys (get json-map exchange))]
     
         

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (time
   (let [exchanges '("ags" "eonly" "int" "nymex" "alt" "comex" "cpc" "cur" "dme" "eqt")]
     (println (get-settlements exchanges)))))

