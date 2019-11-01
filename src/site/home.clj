(ns site.home
  (:require coast
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
  (user/register-user (:email (:params request)))
  (index [:div {:class "main text-center"}
          [:form {:id "login" :action "/login" :method "post"}
           [:h1 {:class "white"} "We've sent you the registration email! Check your inbox ;)"]
           [:div {:class "login-links"}
            [:a {:href "/" :class "white"} "Go back"]]]]))

(defn login-post [request]
  (let [email (:email (:params request))
        password (:password (:params request))
        user-db (coast/q '[:select *
                           :from user
                           :where [email ?email]
                           :limit 1]
                         {:email email})
        user-email-db (:user/email user-db)
        user-password-db (:user/password user-db)]
    (if (and (= email user-email-db) (= password user-password-db))
      (coast/redirect-to :site.home/dashboard)
      (index
       [:div {:class "main text-center"}
        [:form {:id "login" :action "/login" :method "post"}
         [:h1 {:class "white"} "Please sign in"]
         [:div {:class "form-group"}
          [:h3 {:class "white"} "Username or password wrong!"]
          [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
          [:input {:type "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
         [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
         [:div {:class "login-links"}
          [:a {:href "/register" :class "white"} "Register"]
          [:a {:href "/" :class "white"} "Login"]]
         (coast/csrf)]]))))


(defn dashboard [request])


(comment
  (coast/q '[:select *
             :from user])

  )
