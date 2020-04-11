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

(deftask build []
  (comp (perun/global-metadata :filename "site.base.edn")
        (perun/markdown)
        (perun/collection :renderer 'site.index/index-page
                          :page "index.html")
        (perun/render :renderer 'site.index/post-page)
        (perun/static :renderer 'site.about/render
                      :page "about.html")
        (target)))

#_(deftask build
  "Build test blog. This task is just for testing different plugins together."
  []
  (comp
   (perun/global-metadata)
   (perun/markdown)
   (perun/draft)
   (perun/print-meta)
   (perun/slug)
   (perun/ttr)
   (perun/word-count)
   (perun/build-date)
   (perun/gravatar :source-key :author-email :target-key :author-gravatar)
   (perun/render :renderer 'io.perun.example.post/render)
   (perun/collection :renderer 'io.perun.example.index/render :page "index.html")
   (perun/tags :renderer 'io.perun.example.tags/render)
   (perun/paginate :renderer 'io.perun.example.paginate/render)
   (perun/assortment :renderer 'io.perun.example.assortment/render
                     :grouper (fn [entries]
                                (->> entries
                                     (mapcat (fn [entry]
                                               (if-let [kws (:keywords entry)]
                                                 (map #(-> [% entry]) (str/split kws #"\s*,\s*"))
                                                 [])))
                                     (reduce (fn [result [kw entry]]
                                               (let [path (str kw ".html")]
                                                 (-> result
                                                     (update-in [path :entries] conj entry)
                                                     (assoc-in [path :entry :keyword] kw))))
                                             {}))))
   (perun/static :renderer 'io.perun.example.about/render :page "about.html")
   (perun/inject-scripts :scripts #{"start.js"})
   (perun/sitemap)
   (perun/rss :description "Hashobject blog")
   (perun/atom-feed :filterer :original)
   (perun/print-meta)
   (target)
   (notify)))

#_(deftask dev []
  (comp (watch)
        (build)
        (serve :resource-root "public")))

;; https://clojurians-log.clojureverse.org/perun/2016-10-30
;; boot-reload - live-reload of browser Cljs, HTML, CSS and images (Requires Cljs).

(deftask dev []
  (comp ;; (repl :server true)
        (perun/print-meta)
        (watch)
        (build)
        (perun/inject-scripts :scripts #{"js/livereload.js"})
        (livereload :asset-path "public" :filter #"\.(css|html|js)$")
        (serve :resource-root "public")))
