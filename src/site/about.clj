(ns site.about
  (:require [hiccup.page :refer [html5]]
            [site.index :as layout]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (layout/head global-meta)
         [:body
          (layout/header global-meta)
          (layout/render-post { :title "About Me" :content "Born in Moscow in 1821, I was introduced to literature at an early age through fairy tales and legends, and through books by Russian and foreign authors."})]))
