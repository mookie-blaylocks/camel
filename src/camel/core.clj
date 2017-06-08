(ns camel.core
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :as str])
  (use camel.get-ftp)
  (use camel.make-maps)
  (:gen-class))
         

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (time
   ;(let [exchanges '("ags" "eonly" "int" "nymex" "alt" "comex" "cpc" "cur" "dme" "eqt")
   (let [exchanges '("ags")
         settlement-maps (make-settlement-maps exchanges)]
     settlement-maps)))
