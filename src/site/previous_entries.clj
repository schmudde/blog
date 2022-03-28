(ns site.previous-entries
  (:require [hiccup.page :refer [html5]]
            [site.layout :refer [body-template]]
            [site.core :refer [list-posts format-date tags->links]]))

(defn list-posts-temp [posts]
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

(defn make-previous-entries-page [posts]
  [:article
   [:header
    [:h1 "Previous Entries"]
    [:p "Previous entries in " [:a {:href "/" :title "home"} "Beyond the Frame"] "." ]]
   [:div.cf
    [:section.ph3.fl.w-100.w-50-ns
     [:h2 "Previous Posts"]
     [:div
      (list-posts-temp (filter #(= (:type %) "post") posts))]]
    [:section.ph3.fl.w-100.w-50-ns
     [:h2 "Book Reviews"]
     [:div
      (list-posts-temp (filter #(= (:type %) "book") posts))]]]])

(defn render [{global-meta :meta collection-meta :entry posts :entries}]
  (let [page-title "Previous Entries"
        content (make-previous-entries-page posts)]
    (body-template global-meta page-title content)))
