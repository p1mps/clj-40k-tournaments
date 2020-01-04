(ns site.home
  (:require coast
            html
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
        user-db (coast/q '[:select *
                           :from user
                           :where [email ?email]
                           :limit 1]
                         {:email email})
        user-email-db (:user/email user-db)
        user-password-db (:user/password user-db)]
    (if (and (= email user-email-db) (= password user-password-db))
      (coast/redirect-to :site.home/dashboard)
      (-> (html/wrong-login) (html/header)))))


(defn dashboard [request])


(comment
  (coast/q '[:select *
             :from user])

  )
