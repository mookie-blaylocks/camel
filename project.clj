(defproject camel "0.1.0-SNAPSHOT"
  :description "Download CME settlement data"
  :url "http://github.com/mookie-blaylocks/camel"
  :license {:name "GPL 3.0"
            :url "https://www.gnu.org/copyleft/gpl.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.3.443"]
                 [cheshire "5.7.1"]
                 [clj-time "0.13.0"]]
  :java-source-paths ["src/camel"]
  :resource-paths ["lib/commons-net-3.6.jar"]
  :main ^:skip-aot camel.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
