(ns clj-40k-tournaments.client-server
  (:require [cljs-http.client :as http]
            [secretary.core :as secretary]
            [reagent.core :as reagent :refer [render-component]]
            [cljs.core.async :refer [<! go take!]]))

(def host "http://localhost:9500")

(def login-url (str host "/login"))

(defn send-post [{email :email password :password}]
  (go (<! (http/post
           login-url
           {:email email :password password}))))

(defn call-login [data]
  ;;(http/post login-url {:edn-params {:foo :bar}})
  (println "dispatch")
  (println data)
  (send-post data))
  ;; (reagent/render-component [:h1 "login"] (get-app-element))
