(ns site.previous-entries
  (:require [hiccup.page :refer [html5]]
            [site.layout :refer [body-template]]
            [site.core :refer [list-posts format-date]]))

(defn make-previous-entries-page [posts]
  [:div
   [:header
    [:h1 "Previous Entries"]
    [:p "Previous entries in " [:a {:href "/" :title "home"} "Beyond the Frame"] "." ]]
   [:div
    (list-posts posts)]])

(defn render [{global-meta :meta collection-meta :entry posts :entries}]
  (let [page-title "Previous Entries"
        content (make-previous-entries-page posts)]
    (body-template global-meta page-title content)))


(comment

  (def global-data {:base-url "https://schmud.de/"
                    :site-title "Beyond the Frame"
                    :description "The metaphysics of information, art, and narrative"
                    :author "David Schmudde"})
  (def post-data {:canonical-url "✓" :title "✓" :description "✓" :tags ["tag-a" "tag-b"] :content "<body><p>lorem ipsum</p><img src=\"/img/test.png\"></body>"})
  (def tag-data {:canonical-url "✓" :title "✓" :description "✓" :tag "tag-a" :content "<body><p>tags</p></body>"})


  (render {:meta global-data :entry nil :entries [post-data post-data post-data]})
)
