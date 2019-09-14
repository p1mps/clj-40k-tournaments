(ns clj-40k-tournaments.server
  (:require
   [compojure.core :refer [defroutes GET POST]]
   [compojure.route :as route]
   [hiccup.bootstrap.middleware :refer [wrap-bootstrap-resources]]
   [hiccup.bootstrap.page :refer [include-bootstrap]]
   ;;[com.cemerick/friend :as friend]
   [hiccup.page :refer [html5 include-js include-css]]))

(defn index-html []
  (html5
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]
    (include-css "/css/style.css")]
   [:body
    [:div {:id "app" :class "main text-center"}]
    (include-js "https://code.jquery.com/jquery-3.3.1.slim.min.js")
    (include-js "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js")
    (include-js "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js")
    (include-bootstrap)
    (include-js "/cljs-out/dev-main.js")]))

(defn login[]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "ok"})

(defroutes handler
  (GET "/status" _ {:status 200
                    :headers {"Content-Type" "application/json"}
                    :body "ok"})
  (POST "/login" [] (login))
  (GET "/" [] (index-html))
  (route/not-found "<h1>Page not found</h1>"))


(def app
  (-> handler
      (wrap-bootstrap-resources)))
