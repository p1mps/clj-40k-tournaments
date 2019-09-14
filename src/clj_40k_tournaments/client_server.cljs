(ns clj-40k-tournaments.client-server
  (:require [cljs-http.client :as http]
            [clj-40k-tournaments.core :refer [get-app-element]]
            [reagent.core :as reagent :refer [render-component]]
            [cljs.core.async :refer [<!]]))


(def host "http://localhost:9500")

(def login-url (str host "/login"))

(defn call-login []
  ;; (http/post login-url {:edn-params {:foo :bar}})
  ;; (reagent/render-component [:h1 "login"] (get-app-element))
  (println "call login"))
