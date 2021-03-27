(ns site.timeline
  (:require [hiccup.page :refer [html5]]
            #_[site.cv :as formatter]
            [clojure.string :as str]

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

(defn get-timeline-for-post
  "Returns a vector of maps that correspond to the given permalink (post)"
  [permalink]
  (let [article (link->keyword permalink)]
     (article (index-timeline timeline)))) ;; TODO: don't build the timeline every time

#_(get-timeline-for-post "/posts/2021-01-07-truth-storms-the-capitol.html")

(defn timeline-entry-template
  "In: a single timeline map
   Out: a formatted hiccup vector of timeline elements"
  [{:keys [date title desc footer]}]
  [:div {:class "timeline-item" :date-is date :machine-date date}
   [:h2 title]
   [:p desc]
   [:footer footer]])

(defn build-timeline
  "Returns all timeline elements related to a given post, formated and wrapped in a :div tag"
  [permalink]
  (let [timeline-entries (get-timeline-for-post permalink)]
    [:div {:class "timeline"}
     (map #(timeline-entry-template (second %1)) timeline-entries)]))

(build-timeline "/posts/2021-01-07-truth-storms-the-capitol.html")




;; TODO:
;; - make-links etc... from site.cv
;; - output timeline.html
;; - slurp in the edn on the main page build and add timeline elements to the end of a page if the :link matches the filename (e.g. 2021-01-07-truth-storms-the-capitol.html)
