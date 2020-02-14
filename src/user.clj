(ns user
  (:require coast
            email))

(defn rand-str [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn generate-password []
  (rand-str 5))

(defn register-user [user-email]
  (let [password (generate-password)]
    (coast/insert {:user/email user-email :user/password password})
    (email/send-registration-email user-email password)))

(defn logged-in-user [request]
  (let [user-email (get-in request [:session :member/email])]
    (first (coast/q [:select '* :from 'user :where [:user/email user-email]]))))
