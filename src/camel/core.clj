
(ns camel.core
  (use camel.get-ftp)
  (use camel.get-ftp)
  (:gen-class))
         

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (time
   (let [exchanges '("ags" "eonly" "int" "nymex" "alt" "comex" "cpc" "cur" "dme" "eqt")]
     (println (get-settlements exchanges)))))

