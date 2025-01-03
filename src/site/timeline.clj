(ns site.timeline
  (:require [hiccup.page :refer [html5]]
            [site.time-utils :refer [java-time->str java-time->full-date-str]]
            [clojure.string :as str]
            [site.layout :refer [body-template]]
            [clojure.edn :as edn]
            [time-literals.read-write :as time-read]))



(def timeline (->> (slurp "src/site/timeline.edn")
                   (clojure.edn/read-string {:readers time-read/tags})))

(defn link->keyword [link]
  (-> link
      (str/replace ".html" "")
      (str/replace "/posts/" "")
      keyword))

(defn index-timeline [timeline]
  (->> timeline
       (map (juxt #(if-let [link (:link %1)]
                     (link->keyword link)) identity))
       (filter #(some? (first %1))) ; filter out any entry without an associated article
       (group-by #(first %))))

(def ^:private timeline-indexed (index-timeline timeline))

;;;;;;;;;;;;;;;;;;;;;

(defn get-timeline-for-post
  "Returns a vector of maps that correspond to the given permalink (post)"
  [permalink]
  (let [article (link->keyword permalink)]
     (article timeline-indexed)))

(defn timeline-entry-footer [desc footer]
  [:div
   [:p desc " &dagger;"]
   [:footer "&dagger; " footer]])

(defn timeline-entry-template
  "In: a single timeline map
   Out: a formatted hiccup vector of timeline elements"
  ([{:keys [date title desc footer]}]
   [:section {:class "timeline-item" :data-date-is (java-time->full-date-str date) :data-machine-date date}
    [:h2 title]
    (if footer
      (timeline-entry-footer desc footer)
      [:div [:p desc]])])
  ([{:keys [date title desc footer image]} link link-name]
   [:section {:class "timeline-item" :data-date-is (java-time->full-date-str date) :data-machine-date (str date)}
    [:h2 title]
    (if footer
      (timeline-entry-footer desc footer)
      [:div [:p desc]])
    (when link
      [:p "From the article "
       [:i [:a {:href link :title link-name} link-name]]])
    [:img {:src image}]
    ]))

(defn make-timeline-for-post
  "Returns all timeline elements related to a given post, formated and wrapped in a :div tag"
  [permalink]
  (let [timeline-entries (get-timeline-for-post permalink)]
    (when timeline-entries
      [:div {:class "timeline"}
       (map #(timeline-entry-template (second %1)) timeline-entries)])))

(defn make-timeline-page []
  [:div
   [:header
    [:h1 "Timeline"]
    [:p "Significant events in the history of information previously covered on " [:a {:href "/" :title "home"} "Beyond the Frame"] "." ]]
   [:div {:class "timeline"}
    (map #(timeline-entry-template % (:link %) (:link-name %)) timeline)]])

(defn render [{global-meta :meta :as meta}]
  (let [page-title "Timeline"
        content (make-timeline-page)]
    (body-template global-meta page-title content)))


(comment
  (make-timeline-for-post "/posts/2021-01-07-truth-storms-the-capitol.html")
  )
