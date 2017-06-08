(ns camel.get-ftp
  (:import [FTPDownloaderFileDemo]))


(defn get-settlements
  "Get the settlement data from the various CME exchanges by passing a list of exchange abbreviations (e.g. '(\"ags\" \"nymex\"). Settlements are saved in ./resources/settlements"
  ([exchanges]
   (if (empty? exchanges)
     "No exchange settlements requested"
     (get-settlements exchanges '())))
  ([exchanges settlements]
   (if (empty? exchanges)
     settlements
   (let [settle (FTPDownloaderFileDemo/get_file (first exchanges))]
     (do (println settle)
         (get-settlements (rest exchanges) (cons settle settlements)))))))
