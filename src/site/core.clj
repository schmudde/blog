(ns site.core
  (:require [hiccup.page :as page]
            [site.layout :refer [body-template]]
            [site.timeline :as timeline]))

(defn format-date [date]
  (if date (.format (java.text.SimpleDateFormat. "MMMM dd, yyyy") date)))

(defn tags->links [tags]
  [:ul.list.dib.ma0.pa0 {:class "tags"}
   (map (fn [tag] [:li.dib.mr2.f5
                   "&nbsp;#"
                   [:a {:href (str "/tags/" tag ".html")}
                     tag]]) tags)])

(defn list-posts [posts]
  [:ul.liste
   (for [post posts]
     [:li
      [:a {:href (:permalink post)} (:title post)]
      "&nbsp;"
      [:time.f5 {:datetime (:date-published post)}
       (format-date (:date-published post))]])])

(defn time-template [time]
  [:time {:datetime time
          :pubdate "pubdate"
          :itemprop "datePublished"} (format-date time)])

(defn article-template [post]
  (let [timeline (timeline/make-timeline-for-post (:permalink post))]
    [:article {:itemscope "itemscope" :itemtype "http://schema.org/BlogPosting"}
     [:meta {:itemprop "author" :content "David Schmudde"}]
     [:header
      [:a {:href (:permalink post) :title (:title post)}
       [:h1 {:itemprop "headline"} (:title post)]]
      (if (= (:type post) "post")
        [:div
         [:i.mr2 {:class "fa fa-calendar"}] "&nbsp;"
         (time-template (:date-published post))
         [:span.ml4 [:i {:class "fa fa-tags"}] "&nbsp;"] (tags->links (:tags post))])]
     [:section {:role "main" :itemprop "articleBody"}
      (:content post)
      (when timeline
        [:div.embedded-timeline
         [:h1 "Timeline"]
         [:p "Events from this post have been added to a " [:a {:href "/timeline.html" :title "The history of information"} "timeline"] " of significant events in the history of information."
          timeline]])]
     (if (= (:type post) "page") [:div [:span "Last Updated: "]
                                  (time-template (:date-modified post))])]))

(defn render-post-pages [{global-meta :meta post :entry}]
  (let [content [:div (article-template post)]]
    (body-template global-meta post content)))

(defn render-tag-pages [{global-meta :meta tag :entry posts :entries}]
  (let [tag (assoc tag :title (str "Posts Tagged With \"" (:tag tag) "\""))
        content [:div [:h1.tc (str "Posts Tagged As \"" (:tag tag) "\"" )]
                 (list-posts posts)]]
    (body-template global-meta tag content)))

(defn render-index-page [{global-meta :meta collection-meta :entry posts :entries}]
  (let [landing-page-post (assoc (first posts) :type "post")
        content [:div
                 (article-template landing-page-post)
                 [:section {:id "old-posts"}
                  [:h2 "Old Posts"]
                  (list-posts posts)]]]
    (body-template global-meta collection-meta content)))

(comment

  (def global-data {:base-url "https://schmud.de/"
                    :site-title "Beyond the Frame"
                    :description "The metaphysics of information, art, and narrative"
                    :author "David Schmudde"})
  (def post-data {:canonical-url "✓" :title "✓" :description "✓" :tags ["tag-a" "tag-b"] :content "<body><p>lorem ipsum</p><img src=\"/img/test.png\"></body>"})
  (def tag-data {:canonical-url "✓" :title "✓" :description "✓" :tag "tag-a" :content "<body><p>tags</p></body>"})

  (render-post-pages {:meta global-data :entry post-data})

  (render-tag-pages {:meta global-data :entry tag-data})

  (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]})

  (spit "temp.html" (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]}))


  (def xxx "It’s a small world. <img src=\"/img/2020-04-26-ambient-church/live-quadraphonic.jpg\" alt=\"LIVE Quadraphonic album cover\" /><br />")
  (re-find #"<img src=\"\/img[\w\d\s\/-]+.[\w\d]+\"" xxx)
  (->> (re-find #"<img src=\"\/img[\w\d\s\/-]+.[\w\d]+\"" xxx) (re-find #"\/img[\w\d\s\/-]+.[\w\d]+"))

  )
