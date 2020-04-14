(set-env!
 :source-paths #{"src" "content"}
 :resource-paths #{"resources"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]
                 [deraen/boot-livereload "0.2.1"]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]]
         '[deraen.boot-livereload :refer [livereload]])

(defn post? [{:keys [original-path] :as meta}]
  (some-> original-path (.startsWith "posts/")))

(defn published? [{:keys [date-published] :as meta}]
  (if date-published true false))

(deftask build []
  (comp (perun/global-metadata :filename "site.base.edn")
        (perun/markdown)
        (perun/collection :renderer 'site.index/render-index-page :page "index.html"
                          :filterer published?)
        (perun/render :renderer 'site.index/render-post-pages :filterer (and post? published?))
        (perun/tags :renderer 'site.index/render-tag-pages
                    :filterer (and post? published?)
                    :out-dir "public/tags")
        (perun/static :renderer 'site.about/render
                      :page "about.html")
        (perun/rss :filterer post?)
        (target)))

;; TODO: how does the CSS move?
;;           (sift :move {#"martinklepschorg-v2.css" "public/stylesheets/martinklepschorg-v2.css"
;; #"martinklepschorg-v3.css" "public/stylesheets/martinklepschorg-v3.css"})
#_(deftask build
  "Build test blog. This task is just for testing different plugins together."
  []
  (comp
   (perun/ttr)
   (perun/word-count)
   (perun/build-date)
   (perun/gravatar :source-key :author-email :target-key :author-gravatar)
   (perun/paginate :renderer 'io.perun.example.paginate/render)
   (perun/sitemap)
   (perun/atom-feed :filterer :original)
   (notify)))


;; https://clojurians-log.clojureverse.org/perun/2016-10-30
;; boot-reload - live-reload of browser Cljs, HTML, CSS and images (Requires Cljs).

(deftask dev []
  (comp ;; (repl :server true)
        (watch)
        (build)
        (perun/print-meta)
        (perun/inject-scripts :scripts #{"js/livereload.js"})
        (livereload :asset-path "public" :filter #"\.(css|html|js)$")
        (serve :resource-root "public")))
