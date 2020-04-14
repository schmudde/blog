(ns site.index
  (:require [hiccup.page :as page]))

(defn format-date [date]
  (if date (.format (java.text.SimpleDateFormat. "MMMM dd, yyyy") date)))

(defn tags->links [tags]
  [:ul.list.dib.pa0.ma0 {:class "tags"}
   (map (fn [tag] [:li.dib.mr2 [:a.link {:href (str "/tags/" tag ".html")} tag]]) tags)])

(defn list-posts [posts]
  [:ul.tc.list.pl0
   (for [post posts]
     [:li.pv1
      [:a.link {:href (:permalink post)}(:title post)]])])

(defn head-template [global-meta post-title]
  [:head
   [:title (:site-title global-meta) (if post-title (str " | " post-title))]
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:link {:rel "stylesheet" :href "/css/tachyons.min.css"}]])

(defn header-template [global-meta]
   [:nav
    [:a.link.black.b.dib.mr3 {:href "/" :title "Home"} (:site-title global-meta)]
    [:p.dib {:id "site-description"} (:description global-meta)]
    [:ul.list.ma0.pa0
     [:li.dib.mr2 [:a.link {:href "/" :title "Home"} "Home"]]
     [:li.dib.mr2 [:a.link {:href "/about.html" :title "About"} "About"]]
     [:li.dib.mr2 [:a.link {:href "/feed.rss" :title "rss"} "RSS"]]]])

(defn article-template [post]
  [:article
   [:header.tc
    [:h1 (:title post)]
    [:div [:time {:datetime (:date-published post) :pubdate "pubdate"} (format-date (:date-published post))]
     [:span (str ", tags: ")] (tags->links (:tags post))]]
   [:section.pv4 {:role "main"} (:content post)]])

(defn body-template
  [global-meta page-title content]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
              (head-template global-meta page-title)
              [:body.avenir
               [:header.pa5 (header-template global-meta)]
               [:main.ph6 {:role "main"} content]]))

(defn render-post-pages [{global-meta :meta post :entry}]
  (let [page-title (:title post)
        content [:div (article-template post)]]
    (body-template global-meta page-title content)))

(defn render-tag-pages [{global-meta :meta tag :entry posts :entries}]
  (let [page-title (str "Posts Tagged With \"" (:tag tag) "\"")
        content [:div [:h1.tc (str "Posts Tagged As \"" (:tag tag) "\"" )]
                 (list-posts posts)]]
    (body-template global-meta page-title content)))

(defn render-index-page [{global-meta :meta collection-meta :entry posts :entries}]
  (let [page-title "Home"
        content [:div
                 (article-template (first posts))
                 [:section.pa4 {:id "old-posts"}
                  [:h2.tc "Old Posts"]
                  (list-posts posts)]]]
    (body-template global-meta page-title content)))
