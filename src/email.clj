(ns email
  (:require
   [postal.core]
   ))

(def smtp-credentials
  (:smtp-credentials (read-string (slurp "secrets.edn"))))

(defn send-registration-email [user-email password]
  (postal.core/send-message
   {:from "imparato.andrea@gmail.com"
    :to user-email
    :subject "Welcome!"
    :body (str "Registration password: " password)}) )
