(ns ^{:doc "Access The Guardian Open-Platform for sample content"
      :author "Herve Schnegg"}

  test-site.retrieve-content

  (:require [config.core :refer [env]]
            [clj-http.client :as client]
            [cheshire.core :refer :all]))



(def search {"from-date" "2017-05-19"})
(def show {"Show-blocks" "body", "show-tags" "all"})

(def payload (conj search {"api-key" (:api-key (:guardian-api env))}))

(def api-return (client/get (:url (:guardian-api env))
                            {:headers payload}))

(def response ((parse-string (api-return :body)) "response"))

(response "pageSize")
(response "pages")


