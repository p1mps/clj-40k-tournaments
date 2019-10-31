(ns routes
  (:require [coast]
            [components]))

(def routes
  (coast/routes
    (coast/site
      (coast/with-layout :components/layout
        [:get "/" :site.home/login]
        [:get "/register" :site.home/register]
        [:post "/login" :site.home/login-post]
        [:post "/register" :site.home/register-post]))

    ;; (coast/api
    ;;   (coast/with-prefix "/api"
    ;;     [:get "/" :api.home/index]
    ;;     ))


    ))
