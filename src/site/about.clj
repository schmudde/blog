(ns site.about
  (:require [hiccup.page :refer [html5]]
            [site.core :as layout]))

(defn render [{global-meta :meta}]
  (let [page-title "About Me"
        content [:div
                 [:h1.tc "About Me"]
                 [:p.pa4 "Born in Moscow in 1821, I was introduced to literature at an early age through fairy tales and legends, and through books by Russian and foreign authors."]]]
    (layout/body-template global-meta page-title content)))
