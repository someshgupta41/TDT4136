(defproject ex3 "0.1.0-SNAPSHOT"
  :description "A Clojure implementation of A*"
  :url "http://github.com/oyvindrobertsen/TDT4136/ex3"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.priority-map "0.0.5"]
                 [quil "2.2.2"]]
  :main ^:skip-aot core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
