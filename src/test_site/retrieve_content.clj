(ns ^{:doc "Access The Guardian Open-Platform for sample content"
      :author "Herve Schnegg"}

  test-site.retrieve-content

  (:require [config.core :refer [env]]
            [clj-http.client :as client]
            [cheshire.core :refer :all]))


(def api-key {"api-key" (:api-key (:guardian-api env))})
(def search {"tag" "technology/artificialintelligenceai" "from-date" "2017-05-01"})
(def show {"Show-blocks" "body", "show-tags" "all"})

(def query-params (conj search api-key show))

(def api-return (client/get (:url (:guardian-api env))
                            {:query-params query-params}))

(def response ((parse-string (api-return :body)) "response"))

(def page-size (response "pageSize"))
(def pages (response "pages"))


(def article-count 15)

(def required-pages (min pages (Math/ceil (/ article-count page-size))))


(defn query-guardian-api [tag from-date & [page show]]
  (let [search {"tag" tag "from-date" from-date}
        page {"page" (if page page 1)}
        show {"show-blocks" (if show show "")}
        api-key {"api-key" (:api-key (:guardian-api env))}
        url (:url (:guardian-api env))
        query-params (conj search page show api-key)
        api-return (client/get url
                               {:query-params query-params})
        api-return-body (parse-string (api-return :body))
        response (api-return-body "response")]
    response))
  

(defn check-available-content [tag from-date]
  (let [response (query-guardian-api tag from-date)
        available-content {:pages (response "pages")
                           :page-size (response "pageSize")}]
    available-content))


(def r (check-available-content "sport/sport" "2017-06-01"))


(defn retrieve-page [page tag from-date]
  (let [response (query-guardian-api tag from-date page "body")]
    response))
    

(def res (retrieve-page 1 "technology/artificialintelligenceai" "2017-06-01"))
