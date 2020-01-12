(ns html
  (:require coast))

(defn header [body]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]]
  body)

(defn register-form []
  [:div {:class "main text-center"}
   [:form {:id "register" :action "/register" :method "post"}
    [:h1 {:class "white"} "Register"]
    [:div {:class "form-group"}
     (coast/csrf)
     [:input {:name "email" :type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Register" ]
    [:div {:class "login-links"}
     [:a {:href "/register"} "Register"]
     [:a {:href "/"} "Login"]]]])

(defn login-form []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "Please sign in"]
    [:div {:class "form-group"}
     [:input {:type "email" :name "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
     [:input {:type "password" :name "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
    [:div {:class "login-links"}
     [:a {:href "/register" :class "white"} "Register"]
     [:a {:href "/" :class "white"} "Login"]]
    (coast/csrf)]])

(defn register-sent-email []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "We've sent you the registration email! Check your inbox ;)"]
    [:div {:class "login-links"}
     [:a {:href "/" :class "white"} "Go back"]]]])

(defn wrong-login []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "Please sign in"]
    [:div {:class "form-group"}
     [:h3 {:class "white"} "Username or password wrong!"]
     [:input {:type "email" :name "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
     [:input {:type "password" :name "password"  :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
    [:div {:class "login-links"}
     [:a {:href "/register" :class "white"} "Register"]
     [:a {:href "/" :class "white"} "Login"]]
    (coast/csrf)]])
