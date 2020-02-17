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
                  [:get "/dashboard" :site.home/dashboard]
                  ;; GAMES routes
                  ;; create game
                  [:post "/games" :site.home/game-post]
                  ;; get games
                  [:get "/games" :site.home/games]
                  ;; get game
                  [:get "/games/:game-id" :site.home/games-get]
                  ;; edit game
                  [:post "/games/:game-id/edit" :site.home/game-edit]
                  ;; delete game
                  [:post "/games/:game-id/delete" :site.home/game-delete]
                  ;; pair
                  [:post "/pair/:game-id/:user-id" :site.home/pair-post]
                  ;; logout
                  [:post "/logout" :site.home/login-post]
                  )))

    ;; (coast/api
    ;;   (coast/with-prefix "/api"
    ;;     [:get "/" :api.home/index]
    ;;     ))


    ))
