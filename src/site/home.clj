(ns site.home
  (:require coast
            db
            email
            html
            [ring.util.response :as response]
            [hiccup.page :refer [html5 include-css include-js]]
            user))

(defn register [request]
  (-> (html/register-form) (html/header)))

(defn login [request]
  (-> (html/login-form) (html/header)))

(defn register-post [request]
  (user/register-user (:email (:params request)))
  (-> (html/register-sent-email) (html/header)))

(defn login-post [request]
  (let [email (:email (:params request))
        password (:password (:params request))
        user-db (first (coast/q '[:select *
                                  :from user
                                  :where [email ?email]
                                  :limit 1]
                                {:email email}))
        user-email-db (:user/email user-db)
        user-password-db (:user/password user-db)]
    (if (and (= email user-email-db) (= password user-password-db))
      (-> (coast/redirect-to :site.home/dashboard)
          (coast/flash "Logged in")
          (assoc :session {:member/email user-email-db})
          )
      (-> (html/wrong-login) (html/header)))))


(defn dashboard [request]
  (html/header
   (html/dashboard-header (html/dashboard-body) "dashboard")))

(defn games [request]
  (let [games (coast/q '[:select *
                         :from game])
        user-email (get-in request [:session :member/email])
        user-id (:user/id (first (coast/q '[:select id :from user :where  [email ?email]] {:email user-email})))]
    (html/header
     (html/dashboard-header (html/games games user-id) "games"))))

;;  :game-id 1, :user-id 5
(defn pair-post [{:keys [params]}]
  (let [game (db/find-row "game" (:game-id params))
        user (db/find-row "user" (:user-id params))
        game (assoc game :user_2 (:user/id user))]
    (coast/execute! [:update 'game
                     :set [:user-2 (:id user)]
                     :where [:id (:id game)]])
    (coast/redirect-to :site.home/games)))

(defn game-post [request]
  (let [{date :date
         hour :hour
         points :points} (:params request)
        user (user/logged-in-user request)]
    (coast/insert {:game/date date :game/points points :game/hour hour})
    (email/send-game-request-email user date points hour)
    (coast/redirect-to :site.home/dashboard)))

(comment
  (coast/execute! [:update 'game
                   :set [:user-2 1]
                   :where [:id 1]])

  (def params {:date "1" :hour "2"})
  (let [{date :date hour :hour} params]
    hour
    )

  )
