(ns site.core
  (:require [hiccup.page :as page]))

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
      [:a {:href (:permalink post)}(:title post)]
      "&nbsp;"
      [:time.f5 {:datetime (:date-published post)}
       (format-date (:date-published post))]])])

(defn head-template [{:keys [site-title base-url author] :as global-meta}
                     {:keys [title canonical-url] :as page-meta}]
  [:head
   [:title site-title (if title (str " | " title))]
   [:meta {:charset "utf-8"}]
   #_[:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
   [:meta {:http-equiv "Content-Type" :content "text/html"}]
   [:link {:rel "icon" :href "/favicon.ico" :type "image/x-icon"}]
   [:link {:rel "stylesheet" :href"https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"}]

   [:meta {:name "description" :content (or (:description page-meta) (:description global-meta))}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:meta {:name "twitter:card" :content "summary"}]
   [:meta {:name "twitter:creator" :content "@dschmudde"}]
   [:meta {:property "og:url" :content (or canonical-url base-url)}]
   [:meta {:property "og:title" :content (or title site-title)}]
   [:meta {:property "og:author" :content author}]
   [:meta {:property "og:image" :content "http://schmud.de/img/btf-logo.png"}]
   [:meta {:property "og:description" :content (or (:description page-meta) (:description global-meta))}]
   (if title ; if there is a post title, this is an article, otherwise it is a website
     [:meta {:property "og:type":content "article"}]
     [:meta {:property "og:type":content "website"}])
   [:link {:rel "stylesheet" :href "/css/tachyons.min.css"}]
   [:link {:rel "stylesheet" :href "/css/tufte.min.css"}]])

(defn header-template [global-meta]
  [:nav {:role "navigation" :itemscope "itemscope" :itemtype "https://schema.org/SiteNavigationElement"}
   [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"}
    [:p.b.ma0.pa0.nowrap.f-headline.washed-red {:itemprop "publisher"} (:site-title global-meta)]
    [:p.dib
     [:span.mb2 {:id "site-description" :itemprop "about"} (:description global-meta)]
     [:span.mb2 " by "]
     [:span.mb2 {:itemprop "author"} (:author global-meta)]]]
   [:ul.list.ma0.pa0
    [:li.dib.mr2 [:a {:href "/" :title "Home"} "Home"]]
    [:li.dib.mr2 [:a {:href "/pages/now.html" :title "Now Page"} "Now"]]
    [:li.dib.mr2 [:a {:href "/pages/about.html" :title "About"} "About"]]
    [:li.dib.mr2 [:a {:href "/feed.rss" :title "rss"} "RSS"]]]])

(defn time-template [time]
  [:time {:datetime time
          :pubdate "pubdate"
          :itemprop "datePublished"} (format-date time)])

(defn article-template [post]
  [:article {:itemscope "itemscope" :itemtype "http://schema.org/BlogPosting"}
   [:meta {:itemprop "author" :content "David Schmudde"}]
   #_[:link {:itemprop "mainEntityOfPage" :href (:canonical-url post)}]
   [:header
    [:h1 {:itemprop "headline"} (:title post)]
    (if (= (:type post) "post")
      [:div
       [:i.mr2 {:class "fa fa-calendar"}] "&nbsp;"
       (time-template (:date-published post))
       [:span.ml4 [:i {:class "fa fa-tags"}] "&nbsp;"] (tags->links (:tags post))])]
   [:section {:role "main" :itemprop "articleBody"} (:content post)]
   (if (= (:type post) "page") [:div [:span "Last Updated: "]
                                (time-template (:date-published post))])])

(defn body-template
  [global-meta page-meta content]
  (page/html5 {:lang "en" :itemscope "itemscope" :itemtype "http://schema.org/WebPage"}
              (head-template global-meta page-meta)
              [:body
               [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"} (header-template global-meta)]
               [:main {:role "main"} content]]))

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
    (body-template global-meta nil content)))

(comment

  (def global-data {:base-url "http://schmud.de/"
                    :site-title "Beyond the Frame"
                    :description "The metaphysics of information, art, and narrative"
                    :author "David Schmudde"})
  (def post-data {:canonical-url "✓" :title "✓" :description "✓" :tags ["tag-a" "tag-b"]})
  (def tag-data {:canonical-url "✓" :title "✓" :description "✓" :tag "tag-a"})

  (clojure.pprint/pprint (head-template global-data post-data))

  (render-post-pages {:meta global-data :entry post-data})

  (render-tag-pages {:meta global-data :entry tag-data})

  (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]})

  (spit "temp.html" (render-index-page {:meta global-data :entry nil :entries [post-data post-data post-data]}))

  )
