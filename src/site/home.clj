(ns site.home
  (:require coast
            email
            [hiccup.page :refer [html5 include-css include-js]]
            user))

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
          [:form {:id "register" :action "/register" :method "post"}
           [:h1 {:class "white"} "Register"]
           [:div {:class "form-group"}
            (coast/csrf)
            [:input {:name "email" :type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]]
           [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Register" ]
           [:div {:class "login-links"}
            [:a {:href "/register"} "Register"]
            [:a {:href "/"} "Login"]]]]))

(defn login [request]
  (index
         [:div {:class "main text-center"}
          [:form {:id "login" :action "/login" :method "post"}
           [:h1 {:class "white"} "Please sign in"]
           [:div {:class "form-group"}
            [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
            [:input {:type "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
           [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
           [:div {:class "login-links"}
            [:a {:href "/register" :class "white"} "Register"]
            [:a {:href "/" :class "white"} "Login"]]
           (coast/csrf)]]))

(defn register-post [request]
  (email/send-registration-email (:email (:params request)) (user/generate-password))
  (index [:div {:class "main text-center"}
          [:form {:id "login" :action "/login" :method "post"}
           [:h1 {:class "white"} "We sent you the registration email!"]
           [:div {:class "login-links"}
            [:a {:href "/" :class "white"} "Go back"]]]]))

(defn login-post [request]
  (println request))
