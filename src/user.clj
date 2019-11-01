(ns user
  (:require coast
            email))

(defn rand-str [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn generate-password []
  (rand-str 5))

(defn register-user [user-email]
  (let [password (generate-password)]
    (coast/insert {:user/email "test@example.com" :user/password password})
    (email/send-registration-email user-email password)))
