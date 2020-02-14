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
      [:post "/register" :site.home/register-post]
      (coast/with middleware/auth
                  [:post "/games" :site.home/game-post]
                  [:post "/games/:game-id/edit" :site.home/game-edit]
                  [:post "/games/:game-id/delete" :site.home/game-delete]
                  [:post "/pair/:game-id/:user-id" :site.home/pair-post]
                  [:post "/logout" :site.home/login-post]
                  [:get "/games" :site.home/games]
                  [:put "/games/game=id" :site.home/edit-games]
                  [:get "/dashboard" :site.home/dashboard])))

    ;; (coast/api
    ;;   (coast/with-prefix "/api"
    ;;     [:get "/" :api.home/index]
    ;;     ))


    ))
