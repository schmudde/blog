(ns site.about
  (:require [hiccup.page :refer [html5]]
            [site.index :as layout]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (layout/head global-meta)
         [:body
          (layout/header global-meta)
          [:p "This is a demonstration of a static page, for content that won't change."]]))
