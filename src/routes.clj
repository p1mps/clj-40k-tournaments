(ns routes
  (:require [coast]
            [middleware]
            [components]))

(def routes
  (coast/routes
    (coast/site
      (coast/with-layout :components/layout
        [:get "/" :site.home/login]
        [:get "/register" :site.home/register]
        [:post "/login" :site.home/login-post]
        [:post "/register" :site.home/register-post]))
    (coast/with middleware/auth
                [:get "/dashboard" :site.home/dashboard])

    ;; (coast/api
    ;;   (coast/with-prefix "/api"
    ;;     [:get "/" :api.home/index]
    ;;     ))


    ))
