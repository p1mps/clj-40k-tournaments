(ns server
  (:gen-class)
  (:require coast
            [ring.middleware.session.cookie :as cookie]
            routes))

(def app
  (coast/app
   {:session {:store (cookie/cookie-store {:key "28d20edfea7d0961"})}
    :routes routes/routes}))

(defn -main [& [port]]
  (coast/server app {:port port}))

(comment
  (-main))
