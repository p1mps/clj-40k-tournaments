(ns site.home
  (:require
   [coast]
   [hiccup.page :refer [html5 include-js include-css]]
   [hiccup.bootstrap.page :refer [include-bootstrap]]))

(defn index [request]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]
   (include-css "/css/style.css")
   (include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css")]
  [:body
   [:div {:class "main"}]
   [:h1 {:class "tc"}
    "Hello!"]
   (include-js "https://code.jquery.com/jquery-3.3.1.slim.min.js")
   (include-js "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js")
   (include-js "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js")])
