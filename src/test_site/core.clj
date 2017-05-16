(ns test-site.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [hiccup.page :as page]))


(defn test-page [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (page/html5 [:body [:p [:b "Hello from Hiccup"]]])})


(def routes
  (route/expand-routes
   #{["/test-page" :get test-page :route-name :test-page]}))


;; Manage dev server
(defonce server (atom nil))


(def service-map
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   8890})


(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc service-map
                              ::http/join? false)))))


(defn stop-dev []
  (http/stop @server))


(defn restart []
  (stop-dev)
  (start-dev))
