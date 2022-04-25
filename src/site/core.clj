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
  [:ul.list
   (for [post posts]
     [:li
      [:a {:href (:permalink post)} (:title post)]
      "&nbsp;"
      [:time.f5 {:datetime (:date-published post)}
       (format-date (:date-published post))]])])

(defn list-posts-with-metadata [posts]
  [:ul.list.pl0
   (for [post posts]
     [:li.mb4
      [:strong [:a {:href (:permalink post)} (:title post)]]
      [:div.pl2
       [:span.f5 [:i.fa.fa-calendar.mr2]
        [:time {:datetime (:date-published post)}
         (format-date (:date-published post))]]
       "&nbsp;"
       [:span.f5 "&nbsp;" [:i.fa.fa-tags.mr2] (tags->links (:tags post))]]
      [:div.pl2.db.lh-copy.measure (:description post)]])])

(defn make-snippet [content]
  (let [matcher (re-matcher #"<p>(.*?)</p>" content)]
    (str (first (re-find matcher))
         (first (re-find matcher))
         (first (re-find matcher)))))

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

(defn article-template-abbreviated [post]
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
      (make-snippet (:content post))
      [:p "..."]
      [:p "Read the complete post," [:a {:href (:permalink post) :title (:title post)} [:i (:title post)]] " &rarr;"
       ]]]))

(defn render-book-pages [{global-meta :meta book-review :entry}]
  (let [content [:div (article-template book-review)]]
    (body-template global-meta book-review content)))

(defn render-post-pages [{global-meta :meta post :entry}]
  (let [content [:div (article-template post)]]
    (body-template global-meta post content)))

(def tag-definitions
  {:doing "Implementation Details"
   :sts "Science and Technology Studies"
   :personal "My Experiences and Comrades"
   :informatics "Informatics and Computer Science"
   :suchness "The Essence of Living"
   :tools "Useful Software"
   :review "Book Reviews"
   :clojure "Clojure Tutorials and Programs"})


(defn render-tag-pages [{global-meta :meta tag :entry posts :entries}]
  (let [num-of-posts (count posts)
        first-half (+ (quot num-of-posts 2) (rem num-of-posts 2))
        content [:div.cf
                 [:h1.tc (str "Posts Tagged As #" (:tag tag) )]
                 [:h2.tc ((keyword (:tag tag)) tag-definitions)]
                 [:div.pv3
                  [:section.ph1.fl.w-100.w-50-ns
                   [:div
                    (list-posts-with-metadata (take first-half posts))]]
                  [:section.ph1.fl.w-100.w-50-ns
                   [:div
                    (list-posts-with-metadata (drop first-half posts))]]]]]
    (body-template global-meta tag content)))


(defn render-programs-index-page [{global-meta :meta collection-meta :entry posts :entries}]
  (let [landing-page-post (assoc (first posts) :type "post")
        content [:div
                 (article-template-abbreviated landing-page-post)
                 [:section {:id "old-posts"}
                  [:h2 "Other Posts"]
                  (list-posts-with-metadata posts)]]]
    (body-template global-meta collection-meta content)))

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
  (def post-data {:canonical-url "✓" :title "✓" :description "✓" :tags ["tag-a" "tag-b"] :cotnent "<body><p>lorem ipsum</p><img src=\"/img/test.png\"></body>"})
  (def tag-data {:canonical-url "✓" :title "✓" :description "✓" :tag "tag-a" :content "<body><p>tags</p></body>"})

  (->> (prn-str #inst "2020-05-11")
       (clojure.edn/read-string)
       (.format (java.text.SimpleDateFormat. "yyyy")))

  (render-post-pages {:meta global-data :entry post-data})

  (render-tag-pages {:meta global-data :entry tag-data :entries [{:permalink "http"
                                                                  :title "Post 1"
                                                                  :date-published (->> (prn-str #inst "2022-05-11")
                                                                                       (clojure.edn/read-string))
                                                                  :tags ["tag-a"]
                                                                  :description "The first post"}
                                                                 {:permalink "http"
                                                                  :title "Post 2"
                                                                  :date-published (->> (prn-str #inst "2020-05-11")
                                                                                       (clojure.edn/read-string))
                                                                  :tags ["tag-a"]
                                                                  :description "The second post"}
                                                                 {:permalink "http"
                                                                  :title "Post 2"
                                                                  :date-published (->> (prn-str #inst "2020-05-11")
                                                                                       (clojure.edn/read-string))
                                                                  :tags ["tag-a"]
                                                                  :description "The second post"}
                                                                 ]})


  (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]})

  (spit "temp.html" (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]}))


  (def xxx "It’s a small world. <img src=\"/img/2020-04-26-ambient-church/live-quadraphonic.jpg\" alt=\"LIVE Quadraphonic album cover\" /><br />")
  (re-find #"<img src=\"\/img[\w\d\s\/-]+.[\w\d]+\"" xxx)
  (->> (re-find #"<img src=\"\/img[\w\d\s\/-]+.[\w\d]+\"" xxx) (re-find #"\/img[\w\d\s\/-]+.[\w\d]+"))

  (re-find (re-pattern "<p>(.*?)</p>")
           "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit et dolore magna aliqua. Ut enim ad minim veniam.</p>
<p>Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"))
