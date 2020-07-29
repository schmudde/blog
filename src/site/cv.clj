(ns site.cv
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [hiccup.page :as page]
            [site.core :as layout]
            [cljc.java-time.local-date :as ld]
            [time-literals.data-readers :as data-readers]
            [time-literals.read-write :as time-read]
            [cljc.java-time.format.date-time-formatter :as formatter]
            [cljc.java-time.year :as year]
            [cljc.java-time.year-month :as year-month]))

(def recognition (->> (slurp "src/site/recognition.edn")
                      (clojure.edn/read-string {:readers time-read/tags})))
(def projects (->> (slurp  "src/site/projects.edn")
                   (clojure.edn/read-string {:readers time-read/tags})))
(def talks-workshops (->> (slurp "src/site/talks-workshops.edn")
                          (clojure.edn/read-string {:readers time-read/tags})))
(def employment-faculty (->> (slurp "src/site/employment-faculty.edn")
                             (clojure.edn/read-string {:readers time-read/tags})))
(def bio (->> (slurp "src/site/bio.edn")
              (clojure.edn/read-string)))

(defn make-quote [x] [:span "&ldquo;" x "&rdquo;"])

(defn make-link
  ([link-name url]
   (if url
     [:a {:href url} link-name]
     link-name))
  ([link-name url f]
   (if url
     (f [:a {:href url} link-name])
     (f link-name))))

(defn find-entry [entry coll]
  (loop [x coll]
    (if (= (:title (first x)) entry)
      (first x)
      (when (seq x)
        (recur (rest x))))))

(defn java-time->str
  "Given one date, returns a date, given two dates, returns a range"
  ([date-time]
   (cond
     (= (type date-time) java.time.Year) (str (year/get-value date-time))
     (= (type date-time) java.time.YearMonth) (str (year-month/get-year date-time))
     (= (type date-time) java.time.LocalDate) (ld/get-year date-time) #_(str (ld/format java-time (formatter/of-pattern "MMMM dd, yyyy")))
     :else date-time))
  ([date-time-1 date-time-2]
   (cond
     (= (type date-time-1) java.time.Year) (str (year/get-value date-time-1) " - " (year/get-value date-time-2))
     (= (type date-time-1) java.time.YearMonth) (str (year-month/format date-time-1 (formatter/of-pattern "MMMM yyyy"))
                                                     " - " (year-month/format date-time-2 (formatter/of-pattern "MMMM yyyy")))
     :else date-time-1)))

(defn java-time->full-date-str [date-time]
  (cond
    (= (type date-time) java.time.Year) (str (year/get-value date-time))
    (= (type date-time) java.time.LocalDate) (str (ld/format date-time (formatter/of-pattern "MMMM dd, yyyy")))))

;;;;;;;;;;;

(defn edn->hiccup [strong & rest]
  [:p {:class "cv-item"} [:strong strong " "]
   (->> (butlast rest)
        (map #(into (when %[:span % ", "])))
        (into [:span ]))
   [:span (last rest)]])

(defn edn->hiccup-work [title date synopsis tech & rest]
  [:p [:span [:strong title] " " date]
   [:br ]
   (->> (butlast rest)
        (map #(into [:span % ", "]))
        (into [:span ]))
   [:span (last rest)]
   [:br]
   (when tech [:small "Technology: " tech])
   [:span synopsis]])

(defn edn->hiccup-start-with-date [date strong & rest]
  [:p {:class "cv-item"}
   [:span date " "]
   [:strong strong " "]
   (->> (butlast rest)
        (map #(into (when %[:span % ", "])))
        (into [:span ]))
   [:span (last rest)]])

(defn edn->selected-talk [{:keys [highlighted-talks]}]
  (let [talks (find-entry (first highlighted-talks) (:conference-talks talks-workshops))]
    [:div
     [:iframe {:width 560 :height 315
               :src "https://www.youtube.com/embed/HalZfFdWuP4?controls=0"
               :allow "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
               :frameborder 0 :allowfullscreen true}]
     [:p "Selected Talk: " (make-link (:title talks) (:link talks) make-quote) ", " (:org talks) ", " (:geo talks)]]))


(defn itemize-list [matcher-object points]
  "I iterate through a java.util.regex.Matcher.find() object and turn each returned string into an HTML list item."
  (let [single-point (re-find matcher-object)]
    (if (some? single-point)
      (itemize-list matcher-object (conj points [:li {:class "qualification"} single-point]))
      points)))

(defn make-list [synopsis]
  "I create a HTML list. I expect a string of data delimited by semicolons."
  [:span {:class "qualifications"} (itemize-list (re-matcher #"[^~]+" synopsis) [:ul ])])

(defn list-authors
  "1. Always add my name.
   2. If there is more than one author, the data presumably has an 'and' in the string: 'Ineque Sisquana and Sarah Conner': 'Schmudde, David, Ineque Sisquana and Sarah Conner.'
   3. If there is only one other author, add the 'and': 'Schmudde, David and Alex Smith'.
  WARNING: if the name contains an 'and' ('AlexANDer'), then this will not work. It really should be a regex."
  [authors]
  (if (some? authors)
    (if (str/includes? authors "and")
      (str "Schmudde, David, " authors)
      (str "Schmudde, David and " authors))
    "Schmudde, David"))

;;;;;;;;;;;;;;;;;;;;;

(defmulti make-table (fn [x] (first x)))

(defmethod make-table :employment [x]
  (let [employment (second x)]
    (into [:div ]
          (map #(edn->hiccup-work (:title %) (java-time->str (:date-bgn %) (:date-end %))
                                  (make-list (:synopsis %)) (:technology %)
                                  (:org %) (:desc %) (:geo %)) employment))))

(defmethod make-table :workshops [x]
  (let [workshops (second x)]
    (->> workshops
         (map #(edn->hiccup-start-with-date (java-time->str (or (:date %) (:date-end %))) (:org %) (:geo %) (:title %) (make-link (:desc %) (:link %) make-quote)))
         (into [:div]))))

(defmethod make-table :conference-exhibs [x]
  (let [presentations (second x)]
    (->> presentations
         (map #(edn->hiccup-start-with-date
                (java-time->str (or (:date %) (:date-end %)))
                (:org %) (:geo %)
                (make-link (:title %) (:link %) make-quote)))
         (into [:div]))))

(defmethod make-table :affiliations [x]
  (let [affiliations (second x)]
    (into [:div ] (map #(edn->hiccup (:org %) (:geo %) (:title %)
                                     (if (:date-bgn %)
                                       (java-time->str (:date-bgn %) (:date-end %))
                                       (java-time->str (:date %)))) affiliations))))

(defmethod make-table :publications [x]
  (let [publications (second x)
        title (fn [title editor link]
                [:span (make-link title link make-quote)
                 (when editor (str " in " editor  " (ed)")) ", "])
        publisher (fn [publisher] (when publisher (str publisher  ", ")))
        stats (fn [stats] (when stats (str "(" stats ")")))]
    (into [:ul ] (map #(vector :li (list-authors (:co-authors %)) ", "
                               (title (:title %) (:editor %) (:link %))
                               [:em (:publication %) ", "]
                               (publisher (:publisher %))
                               (java-time->full-date-str (:date %)) " "
                               (stats (:stats %))) publications))))


(defmethod make-table :in-the-media [x]
  (let [in-the-media (second x)
        publication (fn [publication editor publisher desc]
                      [:span [:em publication]
                       (when (or editor publisher) (str " by " (or editor publisher)))
                       (when desc (str " (" desc ")")) ", "])]
    (into [:ul ] (map #(vector :li
                               (make-link (:title %) (:link %) make-quote) ", "
                         (publication (:publication %) (:editor %) (:publisher %) (:desc %))
                         (java-time->full-date-str (:date %))) in-the-media))))

;; these italicize the final entry. a common interface?
(defmethod make-table :education [x]
  (let [education (second x)]
    (into [:div ] (map #(edn->hiccup-start-with-date (java-time->str (:date-end %)) (:org %) (:title %) [:em (:desc %)]) education))))

(defmethod make-table :honors-grants-awards [x]
  (let [honors (second x)]
    (into [:div ] (map #(edn->hiccup-start-with-date
                         (java-time->str (or (:date %) (:date-end %)))
                         (:title %) (:org %)
                         (:geo %) [:em (:desc %)]) honors))))

;;;;;;;;;;;;;;;;

(defn talks-workshops->hiccup []
  (let [{:keys [workshops conference-talks]} talks-workshops]
    [:div
     [:h2 "Talks &amp; Workshops"]
     [:h3 "Conference Presentations"]
     (edn->selected-talk bio)
     [:hr]
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
     (make-table [:employment faculty])
     [:h2 "Further Employment"]
     (make-table [:employment employee])]))

(defn recognition->hiccup []
  (let [{:keys [publications exhibitions honors-grants-awards affiliations education training in-the-media]} recognition]
    [:div
     [:h2 "Publications &amp; Exhibitions"]
     [:h3 "Publications"]
     (make-table [:publications publications])
     [:h3 "Exhibitions"]
     (make-table [:conference-exhibs exhibitions])
     [:h2 "Institutional Credentials"]
     [:h3 "Education"]
     (make-table [:education education])
     [:h3 "Honors &amp; Grants"]
     (make-table [:honors-grants-awards honors-grants-awards])
     [:h2 "Appearances in the Media"]
     (make-table [:in-the-media in-the-media])
     [:h2 "Affiliations"]
     (make-table [:affiliations affiliations])
     #_[:h3 "Further Training"]
     #_(into [:div ] (map #(edn->hiccup (:tile %) (:org %) (:geo %) (java-time->str (:date %))) training))
     ]))

;;;;;;;;;;;;;;;;;;

(defn make-bio [{:keys [name title cv geo summary highlighted-talks]}]
  [:header [:div [:h1 name] [:h2 cv] [:h3 title] [:p summary]]])

(defn make-cv []
  [:article
   (make-bio bio)
   [:div (employment-facutly->hiccup)]
   [:div (talks-workshops->hiccup)]
   [:div (recognition->hiccup)]
   #_[:h2 "Projects"]
   #_[:div (projects->hiccup)]])

(defn print-cv []
  (spit "temp.html"
        (page/html5 {:lang "en" :itemscope "itemscope" :itemtype "http://schema.org/WebPage"}
                    [:body
                     [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"}]
                     [:main {:role "main"}
                      (make-cv)]])))

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

  "Say What You Mean"

  (loop [coll (:conference-talks talks-workshops)]
    (if (= (:title (first coll)) "Say What You Mean")
      (first coll)
      (when (seq coll)
        (recur (rest coll)))))

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

  (->> (pop rest)
       (mapv #(into [:span % ", "]))
       (#(conj % [:span (last rest) " "]) )
       (into [:span ]))

  (make-list ((first (employment-faculty :employee)) :synopsis))

  (#(vector :div (list-authors (:co-authors %))
            (make-quote (:title %))
            (when (:editor %) (str "in " (:editor %) " (ed), "))
            [:em (:publication %)]
            (:publisher %)
            (java-time->full-date-str (:date %))
            (when (:stats %) (str "(" (:stats %) ")"))
            )

   (nth (second (first recognition)) 5))

  (let [{:keys [employee]} employment-faculty]
    (->> employee
         (map #(edn->hiccup (java-time->str (or (:date %) (:date-end %))) (:org %)(:geo %) (:title %)))
         (into [:div])))


  (let [{:keys [workshops]} talks-workshops]
    (->> workshops
         (map #(edn->hiccup (make-link (:title %) (:link %))))
         (into [:div])))

  )
