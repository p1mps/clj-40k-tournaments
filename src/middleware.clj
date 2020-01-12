(ns middleware
  (:require [coast]))

(defn auth [handler]
  (fn [request]
    (if (some? (get-in request [:session :member/email]))
      (handler request)
      (coast/unauthorized "Unauthrozied request"))))
