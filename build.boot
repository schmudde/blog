;; DO NOT EDIT
;; This file is tangled from README.org.

(set-env!
 :source-paths #{"src" "content"}
 :resource-paths #{"resources"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [nrepl "0.7.0" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [javax.xml.bind/jaxb-api "2.3.0"] ;; new requirement after local system update
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]
                 [deraen/boot-livereload "0.2.1"]
                 [time-literals "0.1.4"]
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

(defn published?
  "In: {:date-published \"yes\"}"
  [{:keys [date-published] :as meta}]
  (if date-published true false))

(deftask build []
  (comp (perun/global-metadata :filename "site.base.edn")
        (perun/pandoc :cmd-opts ["--from" "markdown" "--to" "html5" "--filter" "pandoc-sidenote"])
        (perun/collection :renderer 'site.core/render-index-page :page "index.html"
                          :filterer (apply every-pred [post? published?]))
        (perun/collection :renderer 'site.core/render-index-page :page "books.html"
                          :filterer (apply every-pred [book? published?]))
        (perun/collection :renderer 'site.core/render-programs-index-page :page "programs.html"
                          :filterer (apply every-pred [program? published?]))
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
        (perun/static :renderer 'site.cv/render
                      :page "cv.html"
                      :meta {:type "page"})
        (perun/static :renderer 'site.timeline/render
                      :page "timeline.html"
                      :meta {:type "page"})
        (perun/collection :renderer 'site.previous-entries/render
                          :page "previous-entries.html"
                          :filterer (apply every-pred [(some-fn book? post? program?) published?]))
        (perun/rss :filterer (apply every-pred [post? published?]))
        (perun/rss :site-title "Beyond the Frame: Clojure" :description "Essays about the Clojure programming language"
                   :filterer (apply every-pred [program? published?]) :filename "btf-clojure-feed.rss")
        (target)))

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
  ; > ({:original-path "posts/fefe", :date-published "avril 14th"}
  ;    {:original-path nil, :date-published "date"}
  ;    {:original-path "po", :date-published "may 14th"}
  ;    {:original-path "posts/zzz", :date-published "may 14th"})
  (filter (or post? published?) pub-path-data)
  ; > ({:original-path "posts/fefe", :date-published "avril 14th"}
  ;    {:original-path "posts/zzz", :date-published "may 14th"})

  (filter (apply every-pred [post? published?]) pub-path-data)
  ; > ({:original-path "posts/fefe", :date-published "avril 14th"}
  ;    {:original-path "posts/zzz", :date-published "may 14th"})

  (map #(and (post? %) (published? %)) pub-path-data) ; (true false false false true)
  (map #(or (post? %) (published? %)) pub-path-data) ; (true true true false true)

  )
