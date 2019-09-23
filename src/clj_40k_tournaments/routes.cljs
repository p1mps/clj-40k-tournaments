(ns clj-40k-tournaments.routes
  (:require [clj-40k-tournaments.core :as core]
            [secretary.core :as secretary :refer-macros [defroute]]
            [clj-40k-tournaments.views :as views]
            [reagent.core :as reagent :refer [render-component]]))

(defroute "/login" {}
  (println "login")
  (reagent/render-component [views/login] (core/get-app-element)))

(defroute "/register" {}
  (reagent/render-component [views/register] (core/get-app-element)))

(defroute "/home" {}
  (reagent/render-component [views/home] (core/get-app-element)))
