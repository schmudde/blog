(ns site.time-utils
  (:require [cljc.java-time.local-date :as ld]
            [cljc.java-time.format.date-time-formatter :as formatter]
            [cljc.java-time.year :as year]
            [cljc.java-time.year-month :as year-month]
            [time-literals.read-write :as time-read]))

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
    (= (type date-time) java.time.YearMonth) (str (year-month/format date-time (formatter/of-pattern "MMMM yyyy")))
    (= (type date-time) java.time.LocalDate) (str (ld/format date-time (formatter/of-pattern "MMMM dd, yyyy")))))

(comment

    (-> "2020-12-02"
      (ld/parse)
      (ld/plus-days 90)
      str)

   (year/get-value
    (clojure.edn/read-string {:readers time-read/tags} (:date (first (:publications recognition)))))



   (ld/format (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2019-12-16\"")  (formatter/of-pattern "MMMM dd, yyyy"))
   (ld/get-year (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2019-12-16\""))

   (->
    (clojure.edn/read-string {:readers time-read/tags} "#time/date \"2011-01-01\"")
    java-time->full-date-str)

   (clojure.edn/read-string {:readers time-read/tags} (time-read/print-date "2015-02-11"))

   (time-read/print-year "2021")
   (time-read/print-year "2021-03")
   (time-read/print-year-month "2021-03")
   (time-read/print-date "2019-12-16")

   ;; (require '[time-literals.data-readers :as data-readers])

   (data-readers/date "2019-12-16") ;; (. java.time.LocalDate parse "2015-02-02")
  (. java.time.LocalDate parse "2015-02-02") ;; #object[java.time.LocalDate 0x23428e1c "2015-02-02"]
  (data-readers/instant "2020-03-03") ;; (. java.time.Instant parse "2020-03-03")

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
