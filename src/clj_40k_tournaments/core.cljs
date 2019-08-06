(ns ^:figwheel-hooks clj-40k-tournaments.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "world!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn register []
  [:h1 "REGISTER"])

(defn login []
  [:form {:class "form-signin"}
   [:h1 {:class "white h3 mb-3 font-weight-normal"} "Please sign in"]
   [:input {:type "email" :id "inputEmail" :class "form-control" :placeholder "Email address" :required ""}]
   [:input {:type "password" :id "inputpassword" :class "form-control" :placeholder "Password" :required ""}]
   [:div {:class "checkbox mb-3"}
    [:button {:class "main-btn btn btn-lg btn-primary btn-block login-btn"} "Login" ]]
   [:div {:class "login-footer"}
    [:a {:href "#" :class "white" :on-click #(register)} "Create New Account"]]
   ])

(defn mount [el]
  (reagent/render-component [login] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
