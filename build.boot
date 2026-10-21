(set-env!
 :source-paths #{"src" "content" "server"}
 :resource-paths #{"resources"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [nrepl "0.7.0" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [javax.xml.bind/jaxb-api "2.3.0"] ;; new requirement after local system update
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]
                 [deraen/boot-livereload "0.2.1"]
                 [time-literals "0.1.4"]
                 [ring/ring-mock "0.4.0"]
                 [cljc.java-time "0.1.11"]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]]
         '[deraen.boot-livereload :refer [livereload]])

(defn page? [{:keys [original-path] :as meta}]
  (if original-path
    (.startsWith original-path "pages/")
    false))

(defn post? [{:keys [original-path] :as meta}]
  (if original-path
    (.startsWith original-path "posts/")
    false))

(defn archive? [{:keys [original-path] :as meta}]
  (when original-path
    (clojure.string/includes? original-path "archive")))

(defn book?
  "In: {:original-path \"books\"}"
  [{:keys [original-path] :as meta}]
  (if original-path
    (.startsWith original-path "books/")
    false))

(defn program?
  "In: {:original-path \"programs\"}"
  [{:keys [original-path] :as meta}]
  (if original-path
    (.startsWith original-path "programs/")
    false))

(defn tagged-clojure?
  "Note: an essay tagged #clojure could exist in /books, /posts, /program"
  [{:keys [tags original-path] :as meta}]
  (when (some #(= "clojure" %) tags)
    true))

(defn published?
  "In: {:date-published \"yes\"}"
  [{:keys [date-published] :as meta}]
  (if date-published true false))

(defn sitemap-page?
  "Published pages with a canonical URL. Leaves out unpublished posts, books and
  programs, source files that aren't written to public/, and the map embed."
  [{:keys [canonical-url path] :as meta}]
  (and canonical-url
       path (.startsWith path "public/")
       (not (.endsWith canonical-url "places-embed.html"))
       (not (and ((some-fn post? book? program?) meta)
                 (not (published? meta))))))

(deftask write-sitemap
  "Writes public/sitemap.xml from the pages' canonical URLs.
  (perun/sitemap's dependency fails to load on current Clojure.)"
  []
  (let [tmp (tmp-dir!)
        iso-date #(.format (java.text.SimpleDateFormat. "yyyy-MM-dd") %)
        escape #(clojure.string/escape % {\& "&amp;" \< "&lt;" \> "&gt;"})
        url-entry (fn [{:keys [canonical-url date-modified]}]
                    (str "  <url><loc>" (escape canonical-url) "</loc>"
                         (when (instance? java.util.Date date-modified)
                           (str "<lastmod>" (iso-date date-modified) "</lastmod>"))
                         "</url>\n"))]
    (with-pre-wrap fileset
      (let [pages (->> (perun/filter-meta-by-ext fileset {:filterer sitemap-page? :extensions [".html"]})
                       (sort-by :canonical-url))
            out (clojure.java.io/file tmp "public" "sitemap.xml")]
        (clojure.java.io/make-parents out)
        (spit out (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                       "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n"
                       (apply str (map url-entry pages))
                       "</urlset>\n"))
        (-> fileset (add-resource tmp) commit!)))))

(deftask build []
  (comp (perun/global-metadata :filename "site.base.edn")
        (perun/pandoc :cmd-opts ["--from" "markdown" "--to" "html5" "--filter" "pandoc-sidenote"])
        (perun/collection :renderer 'site.core/render-index-page :page "index.html"
                          :filterer (apply every-pred [post? published?]))
        (perun/collection :renderer 'site.core/render-index-page :page "books.html"
                          :filterer (apply every-pred [book? published?]))
        (perun/collection :renderer 'site.core/render-programs-index-page :page "programs.html"
                          :filterer (apply every-pred [tagged-clojure? published?]))

        (perun/render :renderer 'site.core/render-post-pages
                      :filterer (apply every-pred [post? published?])
                      :meta {:type "post"})
        (perun/render :renderer 'site.core/render-book-pages
                      :filterer (apply every-pred [book? published?])
                      :meta {:type "book"})
        (perun/render :renderer 'site.core/render-post-pages
                      :filterer (apply every-pred [program? published?])
                      :meta {:type "program"})

        (perun/tags :renderer 'site.core/render-tag-pages
                    :filterer (apply every-pred [(some-fn book? post? program?) published?])
                    :out-dir "public/tags")

        (perun/render :renderer 'site.core/render-post-pages
                      :filterer page?
                      :meta {:type "page"})
      ;;; maps
        (perun/static :renderer 'site.places-map/render
                      :page "places.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-embed
                      :page "places-embed.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-turin
                      :page "turin.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-la
                      :page "la.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-chicago
                      :page "chicago.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-berlin
                      :page "berlin.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-seattle
                      :page "seattle.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-bath
                      :page "bath.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-uk
                      :page "uk.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-nyc
                      :page "nyc.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.places-map/render-misc
                      :page "misc.html"
                      :meta {:type "page"})
       ;;;
        (perun/static :renderer 'site.feeds/render
                      :page "pages/feeds.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.cv/render
                      :page "cv.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.timeline/render
                      :page "timeline.html"
                      :meta {:type "page"})
        (perun/collection :renderer 'site.previous-entries/render
                          :page "previous-entries.html"
                          :filterer (apply every-pred [(some-fn book? post? program?) published?]))
        (perun/rss :filterer (apply every-pred [post? published? #((comp not archive?) %)]))
        (perun/rss :site-title "Beyond the Frame: Clojure" :description "Essays about the Clojure programming language"
                   :filterer (apply every-pred [tagged-clojure? published?]) :filename "btf-clojure-feed.rss")
        ;; :no-clean true preserves timestamps on unchanged files so lftp only
        ;; re-uploads content that actually changed. Trade-off: deleted content
        ;; lingers in target/ until manually cleaned (`rm -rf target/`).
        (write-sitemap)
        (target :no-clean true)))

(deftask dev []
  (comp (watch)
        (build)
        (perun/inject-scripts :scripts #{"js/livereload.js"})
        (livereload :asset-path "public" :filter #"\.(css|html|js)$")
        (serve :resource-root "public")))

(comment

  (published? {:date-published nil})
  (published? {:date-published "avril 14th"})

  (def path-data [{:original-path "posts/fefe"} {:original-path nil} {:original-path "po"} {:original-path "fee/fefef"} {:original-path "posts/zzz"} ])

  (def pub-data [{:date-published "avril 14th"} {:date-published nil} {:date-published "may 14th"}])

  (def pub-path-data [{:original-path "posts/fefe" :date-published "avril 14th"} {:original-path nil :date-published "date"} {:original-path "po" :date-published "may 14th"} {:original-path "fee/fefef" :date-published nil} {:original-path "posts/zzz" :date-published "may 14th"} ])

  (filter post? path-data)
  (filter published? pub-path-data)
  (filterv (and post? published?) pub-path-data)




  (filter (or post? published?) pub-path-data)



  (filter (apply every-pred [post? published?]) pub-path-data)



  (map #(and (post? %) (published? %)) pub-path-data) ; (true false false false true)
  (map #(or (post? %) (published? %)) pub-path-data) ; (true true true false true)

  )
