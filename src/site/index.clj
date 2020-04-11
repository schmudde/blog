(ns site.index
  (:require [hiccup.page :as page]))

(defn head [global-meta & [post]]
  [:head
   [:title (:site-title global-meta) (if post (str " | " (:title post)))]
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:link {:rel "stylesheet" :href "/css/tachyons.min.css"}]]
  )

(defn header [global-meta]
  [:header
   [:nav
    [:h1.tc.f1.db [:a.link.black {:href "/" :title "Home"} (:site-title global-meta)]]
    [:p.tc.f2 (:description global-meta)]
    [:ul.list.tc
     [:li.dib.mr2 [:a.link {:href "/about.html" :title "About"} "About"]]
     [:li.dib.mr2 [:a.link {:href "/feed.rss" :title "rss"} "RSS"]]]]])

(defn render-post [post]
  [:article
   [:header.tc
    [:h2 (:title post)]
    [:p "TODO: Where does this come from?"] ;; seems to be some error in (:content post)? :content does not seem to be immutable.
    [:p (:date-published post)]]
   [:main.pa4 {:role "main"} (:content post)]])


(defn post-page [{global-meta :meta post :entry}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
              (head global-meta post)
              [:body
               (header global-meta)
               (render-post post)]))

(defn index-page [{global-meta :meta posts :entries}]
  (page/html5 {:lang "en" :itemtype "http://schema.org/Blog"}
              (head global-meta)
              [:body.avenir
               (render-post (first posts))
               [:section.pa4
                [:h2.tc "Older Posts"]
                [:ul.list.pl0
                 (for [post posts]
                   [:li
                    [:a {:href (:permalink post)}(:title post)]])]]]))
