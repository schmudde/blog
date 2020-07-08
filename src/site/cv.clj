(ns site.cv
  (:require [clojure.edn :as edn]
            [hiccup.page :as page]
            [site.core :as layout]
            [cljc.java-time.local-date :as ld]
            [time-literals.data-readers :as data-readers]
            [time-literals.read-write :as time-read]
            [cljc.java-time.format.date-time-formatter :as formatter]
            [cljc.java-time.year :as year]
            [cljc.java-time.year-month :as year-month]))

;; TODO: add interviews and press

(def recognition (-> "src/site/recognition.edn" slurp edn/read-string))
(def projects (-> "src/site/projects.edn" slurp edn/read-string))
(def talks-workshops (-> "src/site/talks-workshops.edn" slurp edn/read-string))
(def employment-faculty (-> "src/site/employment-faculty.edn" slurp edn/read-string))

(defn edn->hiccup [strong & rest]
  [:div [:strong strong " "]
   (->> rest
        (map #(into [:span % " "]))
        (into [:span ]))])

(defn edn->hiccup-work [strong & rest]
  [:div [:strong strong " "]
   (->> rest
        (map #(into [:span % " "]))
        (into [:span ]))])

(defn edn->hiccup-pub [strong publication & rest]
  [:div
   [:strong "&ldquo;" strong "&rdquo; "]
   [:em publication " "]
   (->> rest
        (map #(into [:span % " "]))
        (into [:span ]))])

(defn edn->hiccup-start-with-date [date strong geo title & rest]
  [:div
   [:span date " "]
   [:strong strong " "]
   [:span geo ", "]
   [:em title " "]
   (->> rest
        (map #(into [:span % " "]))
        (into [:span ]))])

;;;;;;;

(defn java-time->str
  "Given one date, returns a date, given two dates, returns a range"
  ([date-time ]
   (let [java-time (clojure.edn/read-string {:readers time-read/tags} date-time)]
     (cond
       (= (type java-time) java.time.Year) (str (year/get-value java-time))
       (= (type java-time) java.time.YearMonth) (str (year-month/get-year java-time))
       (= (type java-time) java.time.LocalDate) (ld/get-year java-time) #_(str (ld/format java-time (formatter/of-pattern "MMMM dd, yyyy")))
       :else java-time)))
  ([date-time-1 date-time-2]
   (let [java-time-1 (clojure.edn/read-string {:readers time-read/tags} date-time-1)
         java-time-2 (clojure.edn/read-string {:readers time-read/tags} date-time-2)]
     (str (year-month/format java-time-1 (formatter/of-pattern "MMMM yyyy"))
          " - " (year-month/format java-time-2 (formatter/of-pattern "MMMM yyyy"))
         ))))

(defn java-time->full-date-str [date-time]
  (let [java-time (clojure.edn/read-string {:readers time-read/tags} date-time)]
    (cond
      (= (type java-time) java.time.Year) (str "Upcoming " (year/get-value java-time))
      (= (type java-time) java.time.LocalDate) (str (ld/format java-time (formatter/of-pattern "MMMM dd, yyyy"))))))

;;;;;;;;;;;;;;;;

(defn itemize-list [matcher-object points]
  "I iterate through a java.util.regex.Matcher.find() object and turn each returned string into an HTML list item."
  (let [single-point (re-find matcher-object)]
    (if (some? single-point)
      (itemize-list matcher-object (str points [:li {:class "qualification"} single-point]))
      points)))

(defn make-list [synopsis]
  "I create a HTML list. I expect a string of data delimited by semicolons."
  [:ul {:class "qualifications"} (itemize-list (re-matcher #"[^~]+" synopsis) "")])

#_(make-list (:synopsis (first (:faculty employment-faculty))))

;;;;;;;;;;;;;;;;

(defn talks-workshops->hiccup []
  ;; TODO: lead with date
  (let [{:keys [workshops conference-talks]} talks-workshops]
    [:div
     [:h3 "Conference Presentations"]
     (->> conference-talks
          (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:location %) (:geo %) (:title %)))
          (into [:div]))
     [:h3 "Workshops"]
     (->> workshops
          (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:location %) (:geo %) (:title %)))
          (into [:div]))]))

(defn projects->hiccup []
  (let [project-data (vals projects)]
    (map #(edn->hiccup (:title %) (:synopsis %)) project-data)))


(defn employmee-facutly->hiccup []
  (let [{:keys [employee faculty]} employment-faculty]
    [:div
     [:h2 "Academic Work History"]
     (into [:div ]
           (map #(edn->hiccup-work (:title %) (:subtitle %)
                                   (java-time->str (:date-bgn %) (:date-end %))
                                   (:desc %) (:geo %) (:synopsis %)) faculty))
     [:h2 "Employment"]
     (into [:div ]
           (map #(edn->hiccup-work (:title %) (:subtitle %)
                                   (java-time->str (:date-bgn %) (:date-end %))
                                   (:desc %) (:geo %) (:synopsis %)) employee))]))

(defn recognition->hiccup []
  (let [{:keys [publications exhibitions honors-grants-awards affiliations education training in-the-media]} recognition]
    [:div
     [:h3 "Publications"]
     (into [:div ] (map #(edn->hiccup-pub (:title %) (:publication %) (:publisher %) (java-time->full-date-str (:date %)) (:stats %)) publications))
     [:h3 "Exhibitions"]
     (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (:date %)) (:title %) (:geo %) (:project %)) exhibitions))
     [:h3 "Honors"]
     (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:title %) (:org %) (:type %)) honors-grants-awards))
     [:h3 "Education"]
     (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (:date-end %)) (:title %) (:subtitle %) (:desc %)) education))
     [:h3 "Further Training"]
     (into [:div ] (map #(edn->hiccup (:tile %) (:org %) (:geo %) (java-time->str (:date %))) training))
     [:h3 "Affiliations"]
     (into [:div ] (map #(edn->hiccup (:org %) (:title %)) affiliations))
     [:h3 "In the Media"]
     (into [:div ] (map #(edn->hiccup (:title %) (:publication %) (:type %) (java-time->full-date-str (:date %))) in-the-media))
     ]))

(defn make-cv []
  [:div
   [:div (employmee-facutly->hiccup)]
   [:h2 "Talks &amp; Workshops"]
   [:div (talks-workshops->hiccup)]
   [:h2 "Honors etc.."]
   [:div (recognition->hiccup)]
   [:h2 "Projects"]
   [:div (projects->hiccup)]])

(defn print-cv []
  (spit "temp.html"
        (page/html5 {:lang "en" :itemscope "itemscope" :itemtype "http://schema.org/WebPage"}
                    [:body
                     [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"}]
                     [:main {:role "main"}
                      (make-cv)]])))

(print-cv) ;; TODO: Delete

(defn render [{global-meta :meta :as meta}]
  (let [page-title "Curriculum Vitae"
        content (make-cv)]
    (layout/body-template global-meta page-title content)))

(comment

  (let [{:keys [exhibitions honors-grants-awards affiliations education training]} recognition] (first honors-grants-awards))

  (let [{:keys [workshops conference-talks]} talks-workshops] (first conference-talks))
  (keys projects)
  (get-in projects [:jack-and-the-machine :title])

  (reduce #(str %1 (str [:span %2])) "" '("place" "piece" "past"))

  (reduce (fn [x y] (into [:div x] [:span y])) '("place" "piece" "past"))

  (reduce (fn [x y] (str x " " y)) '("place" "piece" "past" "post"))

  (-> "2020-12-02"
      (ld/parse)
      (ld/plus-days 90)
      str)

   (year/get-value
    (clojure.edn/read-string {:readers time-read/tags} (:date (first (:publications recognition)))))

   (ld/format (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2019-12-16\"")  (formatter/of-pattern "MMMM dd, yyyy"))
   (ld/get-year (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2019-12-16\""))

   (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2011-01-01\"")

  (clojure.edn/read-string {:readers time-read/tags} (time-read/print-date "2015-02-11"))

  (time-read/print-year "2021")
  (time-read/print-year "2021-03")
  (time-read/print-year-month "2021-03")
  (time-read/print-date "2019-12-16")


  (data-readers/date "2019-12-16") ;; (. java.time.LocalDate parse "2015-02-02")
  (. java.time.LocalDate parse "2015-02-02") ;; #object[java.time.LocalDate 0x23428e1c "2015-02-02"]
  (data-readers/instant "2020-03-03") ;; (. java.time.Instant parse "2020-03-03")

  (let [{:keys [conference-talks]} talks-workshops]
    (->> conference-talks
         (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:location %)(:geo %) (:title %)))
         (into [:div])))

  (let [{:keys [conference-talks]} talks-workshops]
    (->> conference-talks
         (map #(edn->hiccup (java-time->str (or (:date %) (:date-end %))) (:location %)(:geo %) (:title %)))
         (into [:div])))

  (def temp   (let [{:keys [conference-talks]} talks-workshops]
                (->> conference-talks
                     )))

  (#(edn->hiccup (java-time->str (or (:date %) (:date-end %))) (:location %)(:geo %) (:title %)) (first temp))

  (->> (pop rest)
       (mapv #(into [:span % ", "]))
       (#(conj % [:span (last rest) " "]) )
       (into [:span ]))

  )
