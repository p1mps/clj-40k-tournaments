(ns clj-40k-tournaments.views
  (:require
   [secretary.core :as secretary]
   [clj-40k-tournaments.client-server :as client]))

(defn login []
  [:form {:id "login"}
   [:h1 {:class "white"} "Please sign in"]
   [:div {:class "form-group"}
    [:label "Email"]
    [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
    [:label "Password"]
    [:input {:type "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]]
   [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5" :on-click (fn [e] (.preventDefault e)
                                                                                  (println "event")
                                                                                  (println e)
                                                                                  (let [form-data {:email (.-value (.getElementById js/document "inputEmail"))
                                                                                                   :password (.-value (.getElementById js/document "inputPassword"))}]
                                                                                    (client/call-login form-data)))} "Login"]
   [:div {:class "login-links"}
    [:a {:href "#" :class "white" :on-click #(secretary/dispatch! "/register")} "Register"]
    [:a {:href "#" :class "white" :on-click #(secretary/dispatch! "/login")} "Login"]]])

(defn register []
  [:form
   [:h1 {:class "white"} "Register"]
   [:div {:class "form-group"}
    [:label "Email"]
    [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
    [:label "Password"]
    [:input {:type "password" :id "inputpassword" :class "form-control" :placeholder "Password" :required ""}]]
   [:button {:class "btn btn-lg btn-primary btn-block login-btn mb-5" :on-click #(println "gg")} "Register" ]

   [:div {:class "login-links"}
    [:a {:href "#" :on-click #(secretary/dispatch! "/register")} "Register"]
    [:a {:href "#" :on-click #(secretary/dispatch! "/login")} "Login"]]])


(defn home []
  [:h1 "home"])
