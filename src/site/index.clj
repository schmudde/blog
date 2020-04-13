(ns site.index
  (:require [hiccup.page :as page]))

(defn format-date [date]
  (if date (.format (java.text.SimpleDateFormat. "MMMM dd, yyyy") date)))

(defn head [global-meta & [post]]
  [:head
   [:title (:site-title global-meta) (if post (str " | " (:title post)))]
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:link {:rel "stylesheet" :href "/css/tachyons.min.css"}]])

(defn header [global-meta]
  [:header.pa5
   [:nav
    [:a.link.black.b.dib.mr3 {:href "/" :title "Home"} (:site-title global-meta)]
    [:p.dib (:description global-meta)]
    [:ul.list.ma0.pa0
     [:li.dib.mr2 [:a.link {:href "/" :title "Home"} "Home"]]
     [:li.dib.mr2 [:a.link {:href "/about.html" :title "About"} "About"]]
     [:li.dib.mr2 [:a.link {:href "/feed.rss" :title "rss"} "RSS"]]]]])

(defn render-post [post]
  [:article
   [:header.tc
    [:h1 (:title post)]
    [:time (format-date (:date-published post))]]
   [:main.ph6.pv4 {:role "main"} (:content post)]])

(defn post-page [{global-meta :meta post :entry}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
              (head global-meta post)
              [:body
               (header global-meta)
               (render-post post)]))

(defn index-page [{global-meta :meta collection-meta :entry posts :entries}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
              (head global-meta)
              [:body.avenir
               (header global-meta)
               (render-post (first posts))
               [:section.pa4 {:id "old-posts"}
                [:h2.tc "Old Posts"]
                [:ul.tc.list.pl0
                 (for [post posts]
                   [:li.pv1
                    [:a.link {:href (:permalink post)}(:title post)]])]]]))
