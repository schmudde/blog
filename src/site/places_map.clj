(ns site.places-map
  (:require [site.layout :refer [body-template]]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str]))

;; ---------------------------------------------------------------------------
;; Config
;; ---------------------------------------------------------------------------

(def blog-dir
  (or (System/getenv "BLOG_DIR") "."))

(defn checkins-path []
  (let [year (.getYear (java.time.LocalDate/now))]
    (io/file blog-dir "server" (str "checkins-" year ".edn"))))

;; ---------------------------------------------------------------------------
;; Data
;; ---------------------------------------------------------------------------

(defn load-checkins []
  (let [f (checkins-path)]
    (if (.exists f)
      (edn/read-string (slurp f))
      [])))

(defn load-turin []
  (let [f (io/file blog-dir "server" "turin.edn")]
    (if (.exists f)
      (edn/read-string (slurp f))
      [])))

(defn ->double [v]
  (when v
    (try (Double/parseDouble (str v))
         (catch Exception _ nil))))

(defn dedupe-places
  "One entry per :place slug. Counts visits, keeps most recent check-in data."
  [checkins]
  (->> checkins
       (group-by :place)
       (map (fn [[_slug entries]]
              (let [sorted (sort-by :timestamp entries)
                    latest (last sorted)]
                (assoc latest
                       :visit-count (count entries)
                       :last-visit  (:timestamp latest)))))
       (filter #(and (->double (:latitude %))
                     (->double (:longitude %))))))

(defn normalize-turin
  "Normalize turin.edn entries to the same shape expected by place->js."
  [entries]
  (->> entries
       (filter #(and (->double (:latitude %))
                     (->double (:longitude %))))
       (map #(assoc % :visit-count 1 :last-visit nil))))

;; ---------------------------------------------------------------------------
;; JS generation
;; ---------------------------------------------------------------------------

(defn place->js [{:keys [place name latitude longitude city country
                          visit-count last-visit]}]
  (format "{name:%s,lat:%s,lon:%s,url:\"/places/%s.html\",city:%s,country:%s,visits:%d,last:%s}"
          (pr-str (or name ""))
          (->double latitude)
          (->double longitude)
          place
          (pr-str (or city ""))
          (pr-str (or country ""))
          (or visit-count 1)
          (pr-str (or (some-> last-visit (subs 0 10)) ""))))

(defn places-js [places]
  (str "var places=["
       (str/join "," (map place->js places))
       "];"))

(defn map-script [places]
  (str
   (places-js places)
   "var map=L.map('map').setView([45.0703,7.6869],13);"
   "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png',"
   "{attribution:'&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'}"
   ").addTo(map);"
   "places.forEach(function(p){"
   "  if(!p.lat||!p.lon) return;"
   "  var visits=p.visits===1?'1 visit':p.visits+' visits';"
   "  var popup='<strong><a href=\"'+p.url+'\">'+p.name+'</a></strong>"
   "<br>'+p.city+(p.country?', '+p.country:'')+'<br>'+visits+(p.last?'<br><small>Last: '+p.last+'</small>':'');"
   "  L.marker([p.lat,p.lon]).addTo(map).bindPopup(popup);"
   "});"))

;; ---------------------------------------------------------------------------
;; Renderer
;; ---------------------------------------------------------------------------

(defn render [{global-meta :meta}]
  (let [checkin-places (-> (load-checkins) dedupe-places)
        turin-places   (-> (load-turin) normalize-turin)
        places         (concat checkin-places turin-places)
        page   {:title        "Places"
                :canonical-url (str (:base-url global-meta) "places.html")
                :description  "Places I have visited"}]
    (body-template
     global-meta
     page
     [:div
      [:h1 "Places"]
      [:link {:rel "stylesheet"
              :href "https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"}]
      [:div#map {:style "height:600px;width:100%;"}]
      [:script {:src "https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"}]
      [:script (map-script places)]])))
