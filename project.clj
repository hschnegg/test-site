(defproject test-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [io.pedestal/pedestal.service "0.5.2"]
                 [io.pedestal/pedestal.route "0.5.2"]
                 [io.pedestal/pedestal.jetty "0.5.2"]
                 [hiccup "1.0.5"]
                 [clj-http "3.5.0"]
                 [cheshire "5.7.1"]
                 [yogthos/config "0.8"]]
  :main ^:skip-aot test-site.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:resource-paths ["config/dev"]}})
