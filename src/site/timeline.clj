(ns site.timeline
  (:require [hiccup.page :refer [html5]]
            [site.core :as layout]
            [site.cv :as formatter]
            [clojure.string :as str]

            [clojure.edn :as edn]
            [clojure.string :as str]
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
       (map (juxt #(link->keyword (:link %1)) identity))
       (into {})))

(index-timeline timeline)

;; TODO:
;; - make-links etc... from site.cv
;; - output timeline.html
;; - slurp in the edn on the main page build and add timeline elements to the end of a page if the :link matches the filename (e.g. 2021-01-07-truth-storms-the-capitol.html)
