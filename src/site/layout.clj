(ns site.layout
  (:require [hiccup.page :as page]))

(def analytics [:script {:data-goatcounter "https://beyondtheframe.goatcounter.com/count"
                         :async true :src "//gc.zgo.at/count.js"}])

(defn extract-image [content]
  (when content
    (when-let [img-tag (re-find #"<img src=\"\/img[\w\d\s\/-]+.[\w\d]+\"" content)]
      (re-find #"\/img[\w\d\s\/-]+.[\w\d]+" img-tag))))

(defn head-template [{:keys [site-title base-url author] :as global-meta}
                     {:keys [title canonical-url content] :as page-meta}]
  [:head
   [:title site-title (if title (str " | " title))]
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "Content-Type" :content "text/html"}]
   [:link {:rel "icon" :href "/favicon.ico" :type "image/x-icon"}]
   [:link {:rel "stylesheet" :href "/css/fontawesome/all.min.css"}]


   [:meta {:name "description" :content (or (:description page-meta) (:description global-meta))}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:meta {:name "twitter:card" :content "summary_large_image"}]
   [:meta {:name "twitter:creator" :content "@dschmudde"}]
   [:meta {:property "og:url" :content (or canonical-url base-url)}]
   [:meta {:property "og:title" :content (or title site-title)}]
   [:meta {:property "og:author" :content author}]
   [:meta {:property "og:image" :content (if-let [img-url (extract-image content)]
                                           (str "http://schmud.de" img-url)
                                           "http://schmud.de/img/btf-logo.svg")}]
   [:meta {:property "og:description" :content (or (:description page-meta) (:description global-meta))}]
   (if title ; if there is a post title, this is an article, otherwise it is a website
     [:meta {:property "og:type":content "article"}]
     [:meta {:property "og:type":content "website"}])
   [:meta {:name "ahrefs-site-verification" :content "9f4e0e681ebce19d5f7a4a7ddbab899c9fbc893c04144bd69fc950c3ad555dd2"}]
   [:link {:rel "stylesheet" :href "/css/tachyons.min.css"}]
   [:link {:rel "stylesheet" :href "/css/tufte.min.css"}]
   [:link {:rel "stylesheet" :href "/css/btf.css"}]

   ;; Webmention
   [:link {:rel "webmention" :href "https://webmention.io/schmud.de/webmention"}]
   [:link {:rel "pingback" :href "https://webmention.io/schmud.de/xmlrpc"}]

   ;; IndieAuth
   [:link {:rel "authorization_endpoint" :href "https://indieauth.com/auth"}]
   [:link {:rel "token_endpoint" :href "https://tokens.indieauth.com/token"}]

   ;; To use Aperture as your Microsub endpoint. Then use Monocle.p3k.io as a reader.
   [:link {:rel "microsub" :href "https://aperture.p3k.io/microsub/560"}]

   ;; Convert Kit "Grove Form"
   #_[:script {:data-uid "3053fcf85a" :src "https://schmudde.ck.page/3053fcf85a/index.js" :async "async"}]


   ;; Twitter
   #_[:script {:src "/js/twitter-website-tag-code-snippet.js"}]

   ;; Meta Pixel Code
   [:script "!function(f,b,e,v,n,t,s) {if(f.fbq)return;n=f.fbq=function(){n.callMethod?  n.callMethod.apply(n,arguments):n.queue.push(arguments)}; if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0'; n.queue=[];t=b.createElement(e);t.async=!0; t.src=v;s=b.getElementsByTagName(e)[0]; s.parentNode.insertBefore(t,s)}(window, document,'script', 'https://connect.facebook.net/en_US/fbevents.js'); fbq('init', '1448753142394531'); fbq('track', 'PageView');"]
   [:noscript [:img {:height 1 :width 1 :style "display:none" :src "https://www.facebook.com/tr?id=1448753142394531&ev=PageView&noscript=1"}]]

   ;; Matomo
   [:script
      "var _paq = window._paq = window._paq || [];
  _paq.push(['trackPageView']);
  _paq.push(['enableLinkTracking']);
  (function() {
    var u='https://beyond-the-frame.matomo.cloud/';
    _paq.push(['setTrackerUrl', u+'matomo.php']);
    _paq.push(['setSiteId', '1']);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.async=true; g.src='https://cdn.matomo.cloud/beyond-the-frame.matomo.cloud/matomo.js'; s.parentNode.insertBefore(g,s);
  })();"

    ]

   ])


(defn footer-template []
  [:div {:class "h-card"}
   [:article.mw5.center.bg-white.br3.pa3.pa4-ns.mv3.ba.b--black-10
    [:div.tc
     [:img.br-100.h4.w4.dib.ba.b--black-05.pa2 {:class "u-photo" :title "avatar" :src "https://s.gravatar.com/avatar/4c272d7a0e4b25c5126a93372d8403b6?s=80"}]
     [:h1.f3.mb2 [:span {:class "p-name"} "David Schmudde"]]
     [:h2.f5.fw4.gray.mt0 [:span {:class "p-org"} "Beyond the Frame"]]
     [:div
      [:span {:class "p-locality"} "Turin, "]
      [:span {:class "p-country-name" :title "Italy"} "IT"]]
     [:div {:class "p-tel"} "+1 (917) 994-1620"]
     ]]
   ;; `no-tufte-underline`: https://github.com/edwardtufte/tufte-css/issues/137
   [:ul.list.ma0.pa0
    [:li.dib.mr2 [:a.link.near-black.hover-silver.dib.h2.w2.mr3.no-tufte-underline {:href "https://schmud.de/" :title "Personal Website" :class "u-url"} [:i {:class "fa fa-home"}]]]
    [:li.dib.mr2 [:a.link.near-black.hover-silver.dib.h2.w2.mr3.no-tufte-underline {:href "https://mastodon.social/@schmudde" :title "Mastodon Profile" :rel "me" :class "u-url"} [:i {:class "fab fa-mastodon"}]]]
    [:li.dib.mr2 [:a.link.near-black.hover-silver.dib.h2.w2.mr3.no-tufte-underline {:href "https://twitter.com/dschmudde" :title "Twitter Profile" :rel "me" :class "u-url"} [:i {:class "fab fa-twitter"}]]]
    [:li.dib.mr2 [:a.link.near-black.hover-silver.dib.h2.w2.mr3.no-tufte-underline {:href "https://github.com/schmudde" :title "GitHub Profile" :rel "me" :class "u-url"} [:i {:class "fab fa-github"}]]]
    [:li.dib.mr2 [:a.link.near-black.hover-silver.dib.h2.w2.mr3.no-tufte-underline {:href "https://www.wikidata.org/wiki/User:Schmudde" :title "Wikidata Profile" :rel "me" :class "u-url"} [:i {:class "fab fa-wikipedia-w"}]]]
    ]])

(defn header-template [global-meta]
  [:nav {:role "navigation" :itemscope "itemscope" :itemtype "https://schema.org/SiteNavigationElement"}
   [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"}

    [:div.flex.flex-wrap.pt4
     [:div.flex.flex-column.flex-row-ns.items-center.center.mh0-ns ;; column on small, row on ns
      [:a.link.no-tufte-underline {:href "/"}
       [:div.dn-ns.db [:img.logo.v-mid.w4.h4.pb4 {:src "/img/btf-logo.svg"}]]] ;; hide on ns
      [:div.flex.flex-column.flex-row-l ;; column on small/medium, row on large
       [:ul.list.ma0.pa0.btf-font
        ;; `no-tufte-underline`: https://github.com/edwardtufte/tufte-css/issues/137
        [:li.mr3.di.b [:a.link.dim.no-tufte-underline {:href "/previous-entries.html" :title "Posts in Beyond the Frame"} "Posts"]]
        [:li.mr3.di.b [:a.link.dim.no-tufte-underline {:href "/programs.html" :title "Posts About the Clojure Programming Language"} "Clojure"]]
        [:li.mr3-ns.mr2.di.b [:a.link.dim.no-tufte-underline {:href "/timeline.html" :title "Significant Dates in the History of Information"} "Timeline"]]]

       [:ul.list.ma0.ph0.pt0.pb0-ns.pb4.btf-font
        [:li.di.mr3.btf-font.b [:a.link.dim.no-tufte-underline {:href "/pages/about.html" :title "About"} "About"]]
        [:li.di.mr3.btf-font.b [:a.link.dim.no-tufte-underline {:href "/pages/now.html" :title "Now Page"} "Now"]]
        [:li.di.mr2.btf-font.fw9 [:a.link.dim.no-tufte-underline {:href "/pages/feeds.html" :title "Subscribe"} "Subscribe"]]]]

      [:a.link.no-tufte-underline {:href "/"} ;; inherits row on ns
       [:span.flex.items-center.ph4-ns
        [:div.flex.flex-column-ns.flex-row {:itemprop "publisher"}
         [:span.f1-ns.f2.tr.btf-font "Beyond"]
         [:span.f1-ns.f2.tr.btf-font "&nbsp;the Frame"]]
        [:img.logo.v-mid.pl4.w4-ns.h4-ns.dn.db-ns {:src "/img/btf-logo.svg"}]]]]]]])

(defn body-template
  [global-meta page-meta content]
  (page/html5 {:lang "en" :itemscope "itemscope" :itemtype "http://schema.org/WebPage"}
              (head-template global-meta page-meta)
              [:body
               [:header {:itemscope "itemscope" :itemtype "https://schema.org/WPHeader"} (header-template global-meta)]
               [:main {:role "main"} content]
               [:footer.pv4.ph3.ph5-ns.tc {:itemscope "itemscope" :itemtype "https://schema.org/WPFooter"}
                (footer-template)]
               analytics]))

(comment

    (clojure.pprint/pprint (head-template global-data post-data)))
