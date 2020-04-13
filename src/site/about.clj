(ns site.about
  (:require [hiccup.page :refer [html5]]
            [site.index :as layout]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (layout/head global-meta)
         [:body.avenir
          (layout/header global-meta)
          [:h1.tc "About Me"]
          [:p.pa4 "Born in Moscow in 1821, I was introduced to literature at an early age through fairy tales and legends, and through books by Russian and foreign authors."]]))
