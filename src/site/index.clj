(ns site.index
  (:require [hiccup.page :as page]))

(defn header [global-meta]
  [:header
   [:h1 [:a {:href "/"} (:site-title global-meta)]]
   [:h2 (:description global-meta)]
   [:nav
    "Menu"
    [:ul
     [:li [:a {:href "/about.html"} "About Page"]]
     [:li [:a {:href "/feed.rss"} "RSS"]]
     [:li [:a {:href "/atom.xml"} "Atom Feed"]]]]])

(defn render [{global-meta :meta posts :entries}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         [:head
          [:title (:site-title global-meta)]
          [:meta {:charset "utf-8"}]
          [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
          [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]]
         [:body
          (header global-meta)
          [:main {:role "main"}
           [:p "Posts"]
           [:ul.items.columns.small-12
            (for [post posts]
              [:li
               [:a {:href (:permalink post)}(:title post)]])]]]))
