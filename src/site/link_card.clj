(ns site.link-card
  "Link cards: static previews of external pages, like a Twitter/Mastodon card.

  In Markdown, write a placeholder link on its own line:

    [Article title](https://example.com/article){.link-card}

  The `link-cards` task in build.boot runs right after Pandoc. At build time the
  page is fetched once, its Open Graph / Twitter metadata is read, and the placeholder is replaced with a card (styled in btf.css). The
  image stays on the remote server. If the page can't be fetched, the
  placeholder is left as an ordinary link."
  (:require [clojure.string :as str]
            [hiccup.core :refer [html]]
            [hiccup.util :refer [escape-html]])
  (:import [org.jsoup Jsoup]))

(defn- meta-content
  "First non-empty content attribute among `selectors`. `attr` is \"content\" for
  text, or \"abs:content\" to resolve a relative URL against the page."
  [doc attr & selectors]
  (some (fn [selector]
          (when-let [el (.selectFirst doc selector)]
            (not-empty (str/trim (.attr el attr)))))
        selectors))

(defn- fetch-card-data* [url]
  (try
    (let [doc (-> (Jsoup/connect url)
                  (.userAgent "Mozilla/5.0 (compatible; schmud.de link card)")
                  (.timeout 10000)
                  (.get))]
      {:title (or (meta-content doc "content" "meta[property=og:title]" "meta[name=twitter:title]")
                  (not-empty (.title doc)))
       :description (meta-content doc "content" "meta[property=og:description]"
                                  "meta[name=twitter:description]"
                                  "meta[name=description]")
       :image (meta-content doc "abs:content" "meta[property=og:image]" "meta[name=twitter:image]")})
    (catch Exception e
      (println "link-card: could not fetch" url "-" (.getMessage e))
      nil)))

;; One fetch per URL per build process, even when `boot dev` re-renders.
(def ^:private fetch-card-data (memoize fetch-card-data*))

(defn- site-name [url]
  (-> (java.net.URI. url) .getHost (str/replace #"^www\." "")))

(defn- card-html [url {:keys [title description image]}]
  (html
   [:a.link-card.no-tufte-underline {:href url}
    (when image
      [:img.link-card-image {:src image :alt ""}])
    [:span.link-card-body
     [:span.link-card-site (escape-html (site-name url))]
     (when title [:span.link-card-title (escape-html title)])
     (when description [:span.link-card-desc (escape-html description)])]]))

(defn expand
  "Replaces every link-card placeholder (`a.link-card`) in an HTML fragment
  with a full card. Fragments without placeholders are returned untouched."
  [content]
  (let [doc (Jsoup/parseBodyFragment (or content ""))
        placeholders (.select doc "a.link-card[href]")]
    (if (.isEmpty placeholders)
      content
      (do
        (doseq [el placeholders
                :let [url (.attr el "href")]]
          (when-let [data (fetch-card-data url)]
            (.before el (card-html url data))
            (.remove el)))
        (.prettyPrint (.outputSettings doc) false)
        (.html (.body doc))))))
