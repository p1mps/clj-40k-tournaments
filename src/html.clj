(ns html
  (:require coast))

(defn header [body]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "/favicon.ico"}]]
  body)

(defn register-form []
  [:div {:class "main text-center"}
   [:form {:id "register" :action "/register" :method "post"}
    [:h1 {:class "white"} "Register"]
    [:div {:class "form-group"}
     (coast/csrf)
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

(defn dashboard-header []
  [:nav.navbar.navbar-expand-lg.navbar-light.bg-light
   [:a.navbar-brand {:href "#"} "Navbar"]
   [:button.navbar-toggler {:type "button", :data-toggle "collapse", :data-target "#navbarSupportedContent", :aria-controls "navbarSupportedContent", :aria-expanded "false", :aria-label "Toggle navigation"} [:span.navbar-toggler-icon]]
   [:div#navbarSupportedContent.collapse.navbar-collapse
    [:ul.navbar-nav.mr-auto
     [:li.nav-item.active
      [:a.nav-link {:href "#"} "Home" [:span.sr-only "(current)"]]]
     [:li.nav-item [:a.nav-link {:href "#"} "Link"]]]]])


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
