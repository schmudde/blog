(ns site.place
  (:require [hiccup.page :as page]
            [site.layout :refer [body-template]]
            [site.core :refer [format-date]]))

(defn render-place-pages [{global-meta :meta place :entry}]
  (let [{:keys [title canonical-url date-published
                address locality region country
                latitude longitude
                place-type opening-hours website
                content]} place
        osm-url (when (and latitude longitude)
                  (str "https://www.openstreetmap.org/?mlat=" latitude
                       "&mlon=" longitude "&zoom=17"))]
    (body-template
     global-meta
     place
     [:div.h-entry
      [:header
       [:h1.p-name title]
       [:time.dt-published {:datetime (str date-published)
                            :itemprop "datePublished"}
        (format-date date-published)]]

      [:div.pl2
       ;; Address block
       (when (or address locality country)
         [:div.p-adr {:itemprop "address"
                      :itemscope "itemscope"
                      :itemtype "https://schema.org/PostalAddress"}
          (when address  [:div.p-street-address {:itemprop "streetAddress"}  address])
          (when locality [:div.p-locality        {:itemprop "addressLocality"} locality])
          (when region   [:div.p-region          {:itemprop "addressRegion"}   region])
          (when country  [:div.p-country-name    {:itemprop "addressCountry"}  country])])

       ;; Hidden lat/lon for mf2 parsers
       (when latitude
         [:data.p-latitude  {:value (str latitude)}])
       (when longitude
         [:data.p-longitude {:value (str longitude)}])

       ;; OSM link
       (when osm-url
         [:div.mt2
          [:a.u-url.link.dim {:href osm-url :rel "noopener" :target "_blank"}
           [:i.fa.fa-map-marker.mr2] "View on OpenStreetMap"]])

       ;; Place type
       (when place-type
         [:div.mt2
          [:span.f6.gray "Type: "] [:span place-type]])

       ;; Opening hours
       (when opening-hours
         [:div.mt2
          [:span.f6.gray "Hours: "] [:span opening-hours]])

       ;; Website
       (when website
         [:div.mt2
          [:a.link.dim {:href website :rel "noopener" :target "_blank"}
           [:i.fa.fa-external-link.mr2] website]])]

      ;; Body content
      (when content
        [:section.mt4 {:role "main" :itemprop "description"}
         content])])))
