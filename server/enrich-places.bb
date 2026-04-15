#!/usr/bin/env bb

;; Enriches server/turin.edn with lat/lon, OSM IDs and extra tags
;; from Nominatim. Skips entries that already have :latitude.
;; Run from the blog root: bb server/enrich-places.bb

(require '[babashka.http-client :as http]
         '[cheshire.core :as json]
         '[clojure.edn :as edn]
         '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[clojure.pprint :refer [pprint]])

(def input-file (or (first *command-line-args*) "server/turin.edn"))
(def user-agent  "BeyondTheFrame/1.0 (+https://schmud.de/) d@schmud.de")
(def nominatim   "https://nominatim.openstreetmap.org/search")
(def rate-limit  1200) ; ms — Nominatim policy: max 1 req/sec

;; ---------------------------------------------------------------------------
;; Nominatim
;; ---------------------------------------------------------------------------

(defn search-nominatim [q]
  (Thread/sleep rate-limit)
  (let [resp (http/get nominatim
               {:query-params {"q"              q
                               "format"         "json"
                               "limit"          "1"
                               "addressdetails" "1"
                               "extratags"      "1"}
                :headers {"User-Agent" user-agent}})]
    (first (json/parse-string (:body resp) true))))

(defn short-name
  "First two words of a name, for broader fallback searches."
  [name]
  (->> (str/split name #"\s+") (take 2) (str/join " ")))

; Turin bounding box — reject results outside this
(def turin-bounds {:lat-min 45.02 :lat-max 45.13
                   :lon-min 7.57  :lon-max 7.77})

(defn within-turin? [result]
  (when-let [lat (some-> (:lat result) Double/parseDouble)]
    (when-let [lon (some-> (:lon result) Double/parseDouble)]
      (and (<= (:lat-min turin-bounds) lat (:lat-max turin-bounds))
           (<= (:lon-min turin-bounds) lon (:lon-max turin-bounds))))))

(defn find-place
  "Tries several query strategies, returns first hit within Turin."
  [{:keys [name city search-hint]}]
  (let [base    [(str name ", " city)              ; "Pastificio Ferri, Turin"
                 (str name ", Torino")              ; Italian city name
                 (str (short-name name) ", Torino") ; "Pastificio Ferri, Torino"
                 ]
        queries (if search-hint (into [search-hint] base) base)]
    (loop [[q & rest-qs] queries]
      (when q
        (println (str "    trying: " q))
        (if-let [result (some-> (search-nominatim q)
                                (#(when (within-turin? %) %)))]
          result
          (recur rest-qs))))))

;; ---------------------------------------------------------------------------
;; Merge result into entry
;; ---------------------------------------------------------------------------

(defn result->map [result entry]
  (let [ext     (get result :extratags {})
        osm-url (str "https://www.openstreetmap.org/"
                     (name (get result :osm_type :node))
                     "/" (:osm_id result))]
    (merge
     entry
     (cond-> {}
       (:lat result)
         (assoc :latitude  (:lat result)
                :longitude (:lon result))
       (:osm_id result)
         (assoc :osm-url osm-url)
       (and (not (:website entry)) (:website ext))
         (assoc :website (:website ext))
       (and (not (:phone entry)) (:phone ext))
         (assoc :phone (:phone ext))
       (and (not (:place-type entry))
            (or (:cuisine ext) (:amenity ext)))
         (assoc :place-type (or (:cuisine ext) (:amenity ext)))))))

;; ---------------------------------------------------------------------------
;; Main
;; ---------------------------------------------------------------------------

(defn enrich-entry [entry]
  (if (:latitude entry)
    (do (println (str "  skip   " (:name entry) " (already enriched)"))
        entry)
    (do
      (println (str "  query  " (:name entry)))
      (if-let [result (find-place entry)]
        (let [enriched (result->map result entry)]
          (println (str "    ✓  " (:lat result) ", " (:lon result)
                        " — " (:osm-url enriched)))
          enriched)
        (do
          (println (str "    ✗  not found in OSM"))
          entry)))))

(defn -main []
  (let [f (io/file input-file)]
    (when-not (.exists f)
      (println "File not found:" input-file)
      (System/exit 1))
    (println (str "Reading " input-file "..."))
    (let [entries  (edn/read-string (slurp f))
          _        (println (str "Enriching " (count entries) " entries...\n"))
          enriched (mapv enrich-entry entries)
          tmp      (io/file (str input-file ".tmp"))]
      (spit tmp (with-out-str (pprint enriched)))
      (.renameTo tmp f)
      (println (str "\nDone. " input-file " updated.")))))

(-main)
