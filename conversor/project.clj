(defproject conversor "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.9.1"]
                 [org.clojure/tools.cli "0.4.1"]
                 [cheshire "5.8.1"]]
  :main ^:skip-aot conversor.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
