(ns site.utils)

(defn make-sidenote [content id]
  [:span
   [:label {:for id :class "margin-toggle sidenote-number"}]
   [:input {:type "checkbox" :id id :class "margin-toggle"}]
   [:span.sidenote content]])

(defn make-marginnote [content id]
  [:span
   [:label {:for id :class "margin-toggle"} "&#8853;"]
   [:input {:type "checkbox" :id id :class "margin-toggle"}]
   [:span.marginnote content]])
