(ns site.post
  (:require [hiccup.page :as page]
            [site.index :as layout]))

(defn render [{global-meta :meta posts :entries post :entry}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         [:head
          [:title (str (:site-title global-meta) "|" (:title post))]
          [:meta {:charset "utf-8"}]
          [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
          [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]]
         [:body
          (layout/header global-meta)
          [:article
           [:header
            [:h1 (:title post)]
            [:p (:date-published post)]]
           [:main {:role "main"} (:content post)]]]))
