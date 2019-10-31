(ns site.home
  (:require
   [coast]
   [hiccup.page :refer [html5 include-js include-css]]
   [hiccup.bootstrap.page :refer [include-bootstrap]]))

(defn index [body]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]]
  [:body
   [:div {:class "main"}
    body]])

(defn register [request]
  (index
         [:div {:class "main text-center"}
          [:form
           [:h1 {:class "white"} "Register"]
           [:div {:class "form-group"}
            [:label "Email"]
            [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
            [:label "Password"]
            [:input {:type "password" :id "inputpassword" :class "form-control" :placeholder "Password" :required ""}]]
           [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Register" ]

           [:div {:class "login-links"}
            [:a {:href "/register"} "Register"]
            [:a {:href "/"} "Login"]]]]))

(defn login [request]
  (index
         [:div {:class "main text-center"}
          [:form {:id "login"}
           [:h1 {:class "white"} "Please sign in"]
           [:div {:class "form-group"}
            [:label "Email"]
            [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
            [:label "Password"]
            [:input {:type "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
           [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
           [:div {:class "login-links"}
            [:a {:href "/register" :class "white"} "Register"]
            [:a {:href "/" :class "white"} "Login"]]]]))
