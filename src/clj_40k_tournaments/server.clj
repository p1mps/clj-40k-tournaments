(ns clj-40k-tournaments.server
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   ;;[com.cemerick/friend :as friend]
   [hiccup.page :refer [html5 include-js include-css]]))

(defn index-html []
  (html5
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    (include-css "/css/style.css")]
   [:body
    [:div {:id "app"}]
    (include-js "/cljs-out/dev-main.js")]))

(defroutes handler
  (GET "/" [] (index-html))
  (route/not-found "<h1>Page not found</h1>"))
