(ns email
  (:require postal.core
            coast
            db))

(def smtp-credentials
  (:smtp-credentials (read-string (slurp "secrets.edn"))))

(defn send-registration-email [user-email password]
  (postal.core/send-message
   {:from "imparato.andrea@gmail.com"
    :to user-email
    :subject "Welcome!"
    :body (str "Registration password: " password)}) )

(defn all-users []
  (coast/q '[:select *
             :from user]))

(defn send-game-request-email [player date points hour]
  (let [user-emails (map :user/email (all-users))]
    (doseq [email user-emails]
      (postal.core/send-message
       {:from "imparato.andrea@gmail.com"
        :to email
        :subject "New game request"
        :body (str "New game request:"
                   "\nplayer:" (:name player)
                   "\nplayer email:" (:email player)
                   "\ndate:" date
                   "\npoints:" points
                   "\nhour:" hour)}))))
