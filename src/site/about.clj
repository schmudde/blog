(ns site.about
  (:require [hiccup.page :refer [html5]]
            [site.layout :refer [body-template]]))


(defn make-sidenote [content id]
  [:span
   [:label {:for id :class "margin-toggle sidenote-number"}]
   [:input {:type "checkbox" :id id :class "margin-toggle"}]
   [:span {:class "sidenote"} content]])

(defn make-marginnote [content id]
  [:span
   [:label {:for id :class "margin-toggle"} "&#8853;"]
   [:input {:type "checkbox" :id id :class "margin-toggle"}]
   [:span {:class "marginnote"} content]])

(def about-template
  [:article
   [:header
    [:h1 "About Me"]
    [:ul.list
     [:li "social links"]]
    [:p "Born in Moscow in 1821, I was introduced to literature at an early age through fairy tales and legends, and through books by Russian and foreign authors."]]
   [:section
    [:h2 "How I Do My Computing"]
    [:h3 "My Tools"]
    [:p "Emacs. " (make-marginnote [:span "Inspired by Richard Stallman's " [:a {:href "https://stallman.org/stallman-computing.html"} "How I do my computing"] "."] "mn-emacs")
     "But there is more to it."]
    [:p "Internet Sites"]
    [:ul
     [:li [:a {:href "https://runbox.com"} "Runbox"] [:span " for eMail."]]]
    [:p "Computer: Linux"]
    [:h3 "What I Do On My Computer"]
    [:p "Text manipulation"]
    [:h3 "This Website"]
    [:ul
     [:li "Blogging: " [:a {:href "https://perun.io/"} "Perun"]]
     [:li "Layout: "
      [:a {:href "https://edwardtufte.github.io/tufte-css/"} "Tufte CSS"]
      (make-sidenote [:span "Inpired by Richard Stallman's " [:a {:href "https://stallman.org/stallman-computing.html"} "How I do my computing"] "."] "sn-emacs")
      " &amp; "
      [:a {:href "http://tachyons.io/"} "Tachyons"]]]
    ]
   [:section "fefe"]])

(defn render [{global-meta :meta :as meta}]
  (let [page-title "About Me"
        content about-template]
     (body-template global-meta page-title content)))
