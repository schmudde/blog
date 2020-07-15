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

(def recognition (-> "src/site/recognition.edn" slurp edn/read-string))
(def projects (-> "src/site/projects.edn" slurp edn/read-string))
(def talks-workshops (-> "src/site/talks-workshops.edn" slurp edn/read-string))
(def employment-faculty (-> "src/site/employment-faculty.edn" slurp edn/read-string))

(defn java-time->str
  "Given one date, returns a date, given two dates, returns a range"
  ([date-time]
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
      (= (type java-time) java.time.Year) (str (year/get-value java-time))
      (= (type java-time) java.time.LocalDate) (str (ld/format java-time (formatter/of-pattern "MMMM dd, yyyy"))))))

;;;;;;;;;;;;;;;;

;; (defprotocol edn->hiccup-output (output [x] "generate x"))
;; (deftype date-first [date title]
;;    edn->hiccup-output )

(defn edn->hiccup [strong & rest]
  [:div [:strong strong " "]
   (->> (butlast rest)
        (map #(into (when %[:span % ", "])))
        (into [:span ]))
   [:span (last rest)]])

(defn edn->hiccup-work [strong & rest]
  [:div [:strong strong " "]
   (->> rest
        (map #(into [:span % " "]))
        (into [:span ]))])

(defn edn->hiccup-start-with-date [date strong & rest]
  [:div
   [:span date " "]
   [:strong strong " "]
   (->> (butlast rest)
        (map #(into [:span % ", "]))
        (into [:span ]))
   [:span (last rest)]])

(defmulti make-table (fn [x] (first x)))

(defmethod make-table :workshops [x]
  (let [workshops (second x)]
    (->> workshops
         (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:org %) (:geo %) (:title %) (:desc %)))
         (into [:div]))))

(defmethod make-table :conference-exhibs [x]
  (let [presentations (second x)]
    (->> presentations
         (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:org %) (:geo %) (str "&ldquo;" (:title %) "&rdquo;")))
         (into [:div]))))

(defmethod make-table :affiliations [x]
  (let [affiliations (second x)]
    (into [:div ] (map #(edn->hiccup (:org %) (:title %)) affiliations))))

(defmethod make-table :publications [x]
  (let [publications (second x)]
    (into [:div ] (map #(edn->hiccup (str "&ldquo;" (:title %) "&rdquo;")
                                     (when (:co-authors %) (str "with " (:co-authors %)))
                                     (when (:publication %)
                                       [:span
                                        [:em (:publication %)]
                                        (when (:editor %) (str " ed. by " (:editor %)))])
                                     (:publisher %)
                                     (java-time->full-date-str (:date %))
                                     #_(if (:date %) (java-time->full-date-str (:date %)) (java-time->str (:date-bgn %) (:date-end %)))
                                     (when (:stats %) (str "(" (:stats %) ")"))) publications))))

(defmethod make-table :in-the-media [x]
  (let [in-the-media (second x)]
    (into [:div ] (map #(edn->hiccup
                         (str "&ldquo;" (:title %) "&rdquo;")
                         [:span
                          (:publication %)
                          (when (or (:editor %) (:publisher %)) (str " by " (or (:editor %) (:publisher %))))
                          (when (:desc %) (str " (" (:desc %) ")"))]
                         (java-time->full-date-str (:date %))) in-the-media))))

;; these italicize the final entry. a common interface?
(defmethod make-table :education [x]
  (let [education (second x)]
    (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (:date-end %)) (:org %) (:title %) [:em (:desc %)]) education))))

(defmethod make-table :honors-grants-awards [x]
  (let [honors (second x)]
    (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:title %) (:org %) [:em (:desc %)]) honors))))


;;;;;;;;;;;;;;;;;;;;;

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
  (let [{:keys [workshops conference-talks]} talks-workshops]
    [:div
     [:h3 "Conference Presentations"]
     (make-table [:conference-exhibs conference-talks])
     [:h3 "Workshops"]
     (make-table [:workshops workshops])]))

(defn projects->hiccup []
  (let [project-data (vals projects)]
    (map #(edn->hiccup (:title %) (:synopsis %)) project-data)))


(defn employment-facutly->hiccup []
  (let [{:keys [employee faculty]} employment-faculty]
    [:div
     [:h2 "Academic Work History"]
     (into [:div ]
           (map #(edn->hiccup-work (:org %) (:title %)
                                   (java-time->str (:date-bgn %) (:date-end %))
                                   (:desc %) (:geo %) (:synopsis %)) faculty))
     [:h2 "Employment"]
     (into [:div ]
           (map #(edn->hiccup-work (:org %) (:title %)
                                   (java-time->str (:date-bgn %) (:date-end %))
                                   (:desc %) (:geo %) (:synopsis %)) employee))]))

(defn recognition->hiccup []
  (let [{:keys [publications exhibitions honors-grants-awards affiliations education training in-the-media]} recognition]
    [:div
     [:h3 "Publications"]
     (make-table [:publications publications])
     [:h3 "Exhibitions"]
     (make-table [:conference-exhibs exhibitions])
     [:h3 "Honors"]
     (make-table [:honors-grants-awards honors-grants-awards])
     [:h3 "Education"]
     (make-table [:education education])
     [:h3 "Further Training"]
     (into [:div ] (map #(edn->hiccup (:tile %) (:org %) (:geo %) (java-time->str (:date %))) training))
     [:h3 "Affiliations"]
     (make-table [:affiliations affiliations])
     [:h3 "In the Media"]
     (make-table [:in-the-media in-the-media])
     ]))

;;;;;;;;;;;;;;;;;;

(defn make-cv []
  [:div
   [:div (employment-facutly->hiccup)]
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
         (map #(edn->hiccup (java-time->str (or (:date %) (:date-end %))) (:org %)(:geo %) (:title %)))
         (into [:div])))

  (def temp   (let [{:keys [conference-talks]} talks-workshops]
                (->> conference-talks
                     )))

  (#(edn->hiccup (java-time->str (or (:date %) (:date-end %))) (:org %) (:geo %) (:title %)) (first temp))

  (->> (pop rest)
       (mapv #(into [:span % ", "]))
       (#(conj % [:span (last rest) " "]) )
       (into [:span ]))

  (make-list ((first (employment-faculty :employee)) :synopsis))
  )
