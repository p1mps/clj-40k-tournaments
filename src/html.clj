(ns html
  (:require coast
            hiccup.page))

(defn header [body]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]
   body])

(defn register-form []
  [:div {:class "main text-center"}
   [:form {:id "register" :action "/register" :method "post"}
    [:h1 {:class "white"} "Register"]
    [:div {:class "form-group"}
     (coast/csrf)
     [:input {:name "name" :type "text" :id "inputName" :class "form-control" :placeholder "Player Name" :required ""}]
     [:input {:name "email" :type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Register" ]
    [:div {:class "login-links"}
     [:a {:href "/register"} "Register"]
     [:a {:href "/"} "Login"]]]])

(defn login-form []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "Please sign in"]
    [:div {:class "form-group"}
     [:input {:type "email" :name "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
     [:input {:type "password" :name "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
    [:div {:class "login-links"}
     [:a {:href "/register" :class "white"} "Register"]
     [:a {:href "/" :class "white"} "Login"]]
    (coast/csrf)]])

(defn register-sent-email []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "We've sent you the registration email! Check your inbox ;)"]
    [:div {:class "login-links"}
     [:a {:href "/" :class "white"} "Go back"]]]])

(defn wrong-login []
  [:div {:class "main text-center"}
   [:form {:id "login" :action "/login" :method "post"}
    [:h1 {:class "white"} "Please sign in"]
    [:div {:class "form-group"}
     [:h3 {:class "white"} "Username or password wrong!"]
     [:input {:type "email" :name "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
     [:input {:type "password" :name "password"  :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
    [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Login"]
    [:div {:class "login-links"}
     [:a {:href "/register" :class "white"} "Register"]
     [:a {:href "/" :class "white"} "Login"]]
    (coast/csrf)]])

;; items [{:href
;;         :text
;;         :active}]
(defn header-links [items active]
  (for [item items]
    (if (= active (:id item))
      [:li.nav-item.active {:id (:id item)}
       [:a.nav-link {:href (:href item)} (:text item) [:span.sr-only "(current)"]]]
      [:li.nav-item {:id (:id item)}
       [:a.nav-link {:href (:href item)} (:text item)]])))

(defn dashboard-header [body active]
  (list [:nav.navbar.navbar-expand-lg.navbar-dark.bg-dark
         [:button.navbar-toggler {:type "button", :data-toggle "collapse", :data-target "#navbarSupportedContent", :aria-controls "navbarSupportedContent", :aria-expanded "false", :aria-label "Toggle navigation"} [:span.navbar-toggler-icon]]
          [:div#navbarSupportedContent.collapse.navbar-collapse
           [:ul.navbar-nav.mr-auto
            (header-links
             [{:href "/dashboard"
               :id "dashboard"
               :text "Request a game"}
              {:href "/games"
               :id "games"
               :text "Games"}] active)]
           [:a.nav-item [:a.nav-link {:href "#"} "Logout"]]]]
        body))


(def form-inline-header
  [:form.form-inline.my-2.my-lg-0
   [:input.form-control.mr-sm-2 {:type "search", :placeholder "Search", :aria-label "Search"}]
   [:button.btn.btn-outline-success.my-2.my-sm-0 {:type "submit"} "Search"]])

(def dropdown-header
  [:li.nav-item.dropdown [:a#navbarDropdown.nav-link.dropdown-toggle {:href "#", :role "button", :data-toggle "dropdown", :aria-haspopup "true", :aria-expanded "false"} "Dropdown"]
   [:div.dropdown-menu {:aria-labelledby "navbarDropdown"}
    [:a.dropdown-item {:href "#"} "Action"]
    [:a.dropdown-item {:href "#"} "Another action"]
    [:div.dropdown-divider] [:a.dropdown-item {:href "#"} "Something else here"]]])

(defn dashboard-body []
  [:div.main.text-center
   [:form {:method "post" :action "/games"}
    [:div.form-group
     [:label.control-label {:for "date"} "Date"]
     [:input#date.form-control
      {:name "date", :placeholder "MM/DD/YYY", :type "text" :required "true"}]]
    [:div.form-group
     [:label {:for "points"} "Points"]
     [:input#hour.form-control
      {:name "points" :type "number" :placeholder "2000" :required "true"}]
     [:label {:for "hour"} "Hour (optional)"]
     [:input#hour.form-control
      {:name "hour" :type "time" :placeholder "18:00" :required "true"}]]
    (coast/csrf)
    [:button.btn.btn-primary {:type "submit"} "Submit"]]])

(defn table-body [games user-id]
  (for [game games]
    [:tr
     [:th {:game/scope "row"} "1"]
     [:td (:game/user game)]
     [:td (:game/user-2 game)]
     [:td (:game/points game)]
     [:td (:game/date game)]
     [:td
      [:div.btn-group
       ;; pair
       (when (nil? (:game/user-2 game) )
         [:form {:action (str "/pair/" (:game/id game) "/" user-id) :method "post"}
          (coast/csrf)
          [:button.btn.btn-primary.button-table {:name "pair" :value "1" :type "submit"} "Pair"]])
       ;; get game
       [:form {:action (str "/games/" (:game/id game)) :method "get"}
        [:button.btn.btn-secondary.button-table  {:type "submit"} "Update"]]
       ;; delete
       [:form {:action (str "/games/" (:game/id game) "/delete") :method "post"}
        (coast/csrf)
        [:button.btn.btn-danger.button-table  {:name "delete" :value "1" :type "submit"} "Delete"]]]]]))

(defn games [games user-id]
  [:div.main.white
   (let [table-data (table-body games user-id)]
     [:table#table.table
      [:thead
       [:tr
        [:th {:scope "col"} "#"]
        [:th {:scope "col"} "Player1"]
        [:th {:scope "col"} "Player2"]
        [:th {:scope "col"} "Points"]
        [:th {:scope "col"} "Date"]
        [:th {:scope "col"} "Action"]]]
      [:tbody
       table-data]])])

(defn show-game [game players]
  [:div.main.white
   [:div.edit-game
    [:form {:id "game" :action (str "/games/" (:game/id game) "/edit" ) :method "post"}
     [:h1 {:class "white"} "Game"]
     [:div.form-group
      [:label {:for "winner"} "Winner"]
      [:select#winner.form-control
       [:option {:name "game/winner" :value (:user/id (:player1 players))} (:user/name (:player1 players))]
       [:option {:name "game/winner" :value (:user/id (:player2 players))} (:user/name (:player2 players))]]]
     [:div.form-group
      [:label {:for "mission"} "Mission (optional)"]
      [:textarea.form-control
       {:name "game/mission" :value (:game/mission game) :type "text" :placeholder "Which mission did you play?"} (:game/mission game)]]
     [:div.form-group
      [:label {:for "battlereport"} "Battle report (optional)"]
      [:textarea#report.form-control
       {:name "game/report" :value (:game/report game) :type "text" :placeholder "Wanna write the battle report?"} (:game/report game)]]
     (coast/csrf)

     [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5"} "Save" ]]]])
