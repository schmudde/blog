(ns site.places-map
  (:require [site.layout :refer [body-template]]
            [hiccup.page :as page]
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

(defn load-edn-places
  "Load a static places EDN file from the server directory by filename."
  [filename]
  (let [f (io/file blog-dir "server" filename)]
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

(defn normalize-static-places
  "Normalize static EDN place entries to the same shape expected by place->js."
  [entries]
  (->> entries
       (filter #(and (->double (:latitude %))
                     (->double (:longitude %))))
       (map #(assoc % :visit-count 1 :last-visit nil))))

(defn turin-places []
  (let [checkin-places (-> (load-checkins) dedupe-places)
        static-places  (-> (load-edn-places "turin.edn") normalize-static-places)]
    (concat checkin-places static-places)))

(defn la-places []
  (-> (load-edn-places "la.edn") normalize-static-places))

(defn all-places []
  (concat (turin-places) (la-places)))

;; ---------------------------------------------------------------------------
;; JS generation
;; ---------------------------------------------------------------------------

(defn place->js [{:keys [name latitude longitude city country
                          visit-count last-visit website notes rating osm-url map-url]}]
  (format "{name:%s,lat:%s,lon:%s,city:%s,country:%s,visits:%d,last:%s,website:%s,notes:%s,rating:%s,osmUrl:%s,mapUrl:%s}"
          (pr-str (or name ""))
          (->double latitude)
          (->double longitude)
          (pr-str (or city ""))
          (pr-str (or country ""))
          (or visit-count 1)
          (pr-str (or (some-> last-visit (subs 0 10)) ""))
          (pr-str (or website ""))
          (pr-str (or notes ""))
          (if rating (str rating) "null")
          (pr-str (or osm-url ""))
          (pr-str (or map-url ""))))

(defn marker-js
  "Emit a single JS statement adding a marker for `p` to `map-var`."
  [map-var p]
  (str "L.marker([" (->double (:latitude p)) ", " (->double (:longitude p)) "])"
       ".addTo(" map-var ")"
       ".bindPopup(makePopup(" (place->js p) "));"))

(def popup-js
  "function makePopup(p) {
    var visits = p.visits === 1 ? '1 visit' : p.visits + ' visits';
    var title  = p.website
      ? '<strong><a href=\"' + p.website + '\">' + p.name + '</a></strong>'
      : '<strong>' + p.name + '</strong>';
    return title
      + '<br>' + p.city + (p.country ? ', ' + p.country : '')
      + (p.notes  ? '<br><em>'    + p.notes          + '</em>'   : '')
      + (p.rating ? '<br>Rating: ' + p.rating + '/5'             : '')
      + '<br>' + visits
      + (p.last ? '<br><small>Last: ' + p.last + '</small>' : '')
      + ((p.osmUrl || p.mapUrl) ? '<br>' : '')
      + (p.osmUrl ? '<a href=\"' + p.osmUrl + '\">OSM</a>' : '')
      + (p.mapUrl ? (p.osmUrl ? ' &middot; ' : '') + '<a href=\"' + p.mapUrl + '\">Maps</a>' : '');
  }")

(def city-views
  "Default [lat lon zoom] for each named city page."
  {:turin [45.0703  7.6869  13]
   :la    [34.0522 -118.2437 11]
   :world [20.0    0.0       2]})

(defn init-map-js
  "Emit JS that creates a Leaflet map in `div-id`, populated from `places`.
  `view` is a [lat lon zoom] vector."
  [map-var div-id view places]
  (let [[lat lon zoom] view
        init-map   (str "var " map-var " = L.map('" div-id "').setView([" lat ", " lon "], " zoom ");")

        ;; add the OpenStreetMap tile layer
        tile-layer (str "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'
  }).addTo(" map-var ");")

        ;; one marker statement per place, built in Clojure
        markers    (str/join "\n" (map #(marker-js map-var %) places))]
    (str/join "\n" [init-map tile-layer markers])))

(defn map-script
  "Emit all JS for the dual map UI. `view` is a [lat lon zoom] vector."
  [view places]
  (let [top-places (filter #(= 5 (:rating %)) places)]
    (str/join "\n" [popup-js
                    (init-map-js "mapAll" "map-all" view places)
                    (init-map-js "mapTop" "map-top" view top-places)])))

;; ---------------------------------------------------------------------------
;; Renderers
;; ---------------------------------------------------------------------------

(defn map-ui
  "Shared hiccup fragment: filter controls + map divs + Leaflet scripts."
  [view places & [{:keys [style]}]]
  [[:input {:type "radio" :id "filter-all" :name "places-filter" :checked true}]
   [:input {:type "radio" :id "filter-top" :name "places-filter"}]
   [:div.places-filter (when style {:style style})
    [:label {:for "filter-all"} "All Places"]
    [:label {:for "filter-top"} "5/5 Only"]]
   [:div.map-wrap (when style {:style "height:calc(100vh - 3rem);"})
    [:div#map-all]
    [:div#map-top]]
   [:script {:src "https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"}]
   [:script (map-script view places)]])

(def leaflet-css
  [:link {:rel "stylesheet" :href "https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"}])

(defn render-embed
  "Bare map page for embedding via <iframe> — no site header or footer."
  [_ & [{:keys [view] :or {view (:turin city-views)}}]]
  (let [places (all-places)]
    (page/html5 {:lang "en"}
      [:head
       [:meta {:charset "utf-8"}]
       [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
       leaflet-css
       [:link {:rel "stylesheet" :href "/css/buttons.css"}]
       [:link {:rel "stylesheet" :href "/css/places.css"}]]
      (into [:body {:style "margin:0;padding:0;"}]
            (map-ui view places [{:style "padding:0.5rem;"}])))))

(defn render-city-page
  "Render a full site page centred on `view` with the given `title` and `places`."
  [global-meta slug title view places]
  (let [page {:title         title
              :canonical-url (str (:base-url global-meta) slug ".html")
              :description   (str "Places I have visited in " title)}]
    (body-template
     global-meta
     page
     (into [:div
            [:h1.btf-font title]
            leaflet-css
            [:link {:rel "stylesheet" :href "/css/buttons.css"}]
            [:link {:rel "stylesheet" :href "/css/places.css"}]]
           (map-ui view places)))))

(defn render [{global-meta :meta}]
  (render-city-page global-meta "places" "Places" (:turin city-views) (all-places)))

(defn render-turin [{global-meta :meta}]
  (render-city-page global-meta "turin" "Turin" (:turin city-views) (turin-places)))

(defn render-la [{global-meta :meta}]
  (render-city-page global-meta "la" "Los Angeles" (:la city-views) (la-places)))
