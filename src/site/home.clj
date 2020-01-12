(ns site.home
  (:require coast
            html
            [ring.util.response :as response]
            [hiccup.page :refer [html5 include-css include-js]]
            user))

(defn register [request]
  (-> (html/register-form) (html/header)))

(defn login [request]
  (-> (html/login-form) (html/header)))

(defn register-post [request]
  (user/register-user (:email (:params request)))
  (-> (html/register-sent-email) (html/header)))

(defn login-post [request]
  (let [email (:email (:params request))
        password (:password (:params request))
        user-db (first (coast/q '[:select *
                                  :from user
                                  :where [email ?email]
                                  :limit 1]
                                {:email email}))
        user-email-db (:user/email user-db)
        user-password-db (:user/password user-db)]
    (if (and (= email user-email-db) (= password user-password-db))
      (-> (coast/redirect-to :site.home/dashboard)
          (coast/flash "Logged in")
          (assoc :session {:member/email user-password-db}))
      (-> (html/wrong-login) (html/header)))))


(defn dashboard [request]
  (-> (html/dashboard-header) (html/header)))


(comment
  (coast/q '[:select *
             :from user])

  )
