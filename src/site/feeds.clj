(ns site.feeds
  (:require [site.layout :refer [body-template]]
            [site.utils :refer [make-marginnote]]))

;; ---------------------------------------------------------------------------
;; Sections
;; ---------------------------------------------------------------------------

(def vmail-figure
  [:figure
   [:img {:src "/img/feeds/v-mail.jpg"}]
   (make-marginnote
    [:span
     [:em [:a {:href "https://www.loc.gov/pictures/item/2017696446/"} "V-Mail"]]
     " (Feb 1943): The image depicts \u201cV-mail\u201d which is being \u201cinspected for flaws
     on an enlarging \u2018reader\u2019 at the Pentagon building, Washington, D.C. V-mail is
     available to and from the armed forces stationed outside the United States. It is only
     1/65th the weight of ordinary mail and saves ninety-eight percent of the cargo space
     required for ordinary letters. 1,600 letters can be placed on a roll of film little
     larger than a pack of cigarettes.\u201d"]
    "mn-vmail")
   [:br]
   [:span {:property "license"
           :xmlns:cc "http://creativecommons.org/ns#"
           :xmlns:dct "http://purl.org/dc/terms/"}
    [:a.no-tufte-underline {:href "https://creativecommons.org/public-domain/"
                            :target "_blank"
                            :rel "license noopener noreferrer"}
     [:i {:class "fab fa-creative-commons-pd-alt"}]]]])

(def email-form
  [:section
   [:h2 [:span {:class "fas fa-envelope"}] "\u00a0eMail"]
   [:form {:action "https://app.kit.com/forms/2884750/subscriptions?redirect_to=https://schmud.de/pages/subscribed.html"
           :class "seva-form formkit-form"
           :method "post"
           :data-sv-form "2884750"
           :data-uid "6bb8abca74"
           :data-format "inline"
           :data-version "6"}
    [:div {:data-style "clean"}
     [:ul.formkit-alert.formkit-alert-error
      {:data-element "errors" :data-group "alert"}]
     [:div.seva-fields.formkit-fields
      {:data-element "fields" :data-stacked "false"}
      [:div.formkit-field
       [:input.formkit-input
        {:name "email_address"
         :aria-label "Email Address"
         :placeholder "Email Address"
         :required true
         :type "email"}]]
      [:button.formkit-submit
       {:data-element "submit"}
       [:span "Subscribe"]]]]
    [:style
     ".formkit-form[data-uid='6bb8abca74'] { max-width: 700px; }
      .formkit-form[data-uid='6bb8abca74'] [data-style='clean'] { width: 100%; }
      .formkit-form[data-uid='6bb8abca74'] .formkit-fields {
        display: flex; flex-wrap: wrap; margin: 0 auto;
      }
      .formkit-form[data-uid='6bb8abca74'] .formkit-field,
      .formkit-form[data-uid='6bb8abca74'] .formkit-submit {
        margin: 0 0 15px 0; flex: 1 0 100%;
      }
      .formkit-form[data-uid='6bb8abca74'] .formkit-input {
        width: 100%; font-size: 15px; padding: 12px;
        border: 1px solid #e3e3e3; border-radius: 4px;
        background: #ffffff; box-sizing: border-box;
      }
      .formkit-form[data-uid='6bb8abca74'] .formkit-input:focus {
        outline: none; border-color: #1677be;
      }
      .formkit-form[data-uid='6bb8abca74'] .formkit-submit {
        border: 0; border-radius: 4px; cursor: pointer;
        font-size: 15px; font-weight: 400; padding: 12px 24px;
        color: #ffffff; background-color: #800020;
      }
      .formkit-form[data-uid='6bb8abca74'] .formkit-submit:hover { opacity: 0.9; }
      @media (min-width: 500px) {
        .formkit-form[data-uid='6bb8abca74'] .formkit-field {
          flex: 100 1 auto; margin-right: 5px;
        }
        .formkit-form[data-uid='6bb8abca74'] .formkit-submit { flex: 1 1 auto; }
      }"]]
   [:p "The newsletter is low traffic \u2014 about five to seven eMails a year."]])

(def web-section
  [:section
   [:hr]
   [:h2 [:span {:class "fas fa-globe"}] "\u00a0Web"]
   [:p "Or follow " [:em "Beyond the Frame"] " around the web:"]
   [:ul
    [:li [:i {:class "fas fa-rss"}] " "
     [:a {:href "/feed.rss"} "RSS"] " is my favorite. There are two feeds:"
     [:ul
      [:li "All " [:a {:href "/feed.rss"} "essays"] " on Beyond the Frame"]
      [:li "All " [:a {:href "/btf-clojure-feed.rss"} "Clojure posts"] " on Beyond the Frame"]]]
    [:li [:i {:class "fab fa-mastodon"}] " "
     [:a {:href "https://mastodon.social/@schmudde"} "Mastodon"]]
    [:li [:i {:class "fab fa-twitter"}] " "
     [:a {:href "https://x.com/dschmudde"} "Xwitter"]]]])

(defn post-item [url title description date tags]
  [:li.mb3
   [:a {:href url} title] ": " description
   [:div.f5 [:i {:class "fa fa-calendar mr2"}] date]
   [:div.f5 [:i {:class "fa fa-tags mr2"}] tags]])

(def greatest-hits
  [:section
   [:hr]
   [:figure
    [:img {:src "/img/feeds/computer-lab.png"}]
    [:span.marginnote
     [:strong "Lynne Cohen"] " " [:em "Computer School, Pittsburgh"] " (1980)"]]
   [:h2 [:span {:class "fa fa-pen"}] "\u00a0Greatest Hits"]
   [:p "If you\u2019re not quite sure what you\u2019re signing up for, here are the top
        five most-read posts on " [:em "Beyond the Frame"] ":"]
   [:ol
    (post-item "https://schmud.de/posts/2024-01-23-untangling-non-linearity.html"
               "Untangling Non-Linearity"
               "How the simple link became the foundation for artificial intelligence and all dynamic media."
               "January 23, 2024"
               "#sts, #informatics, #filmmaking")
    (post-item "https://schmud.de/posts/2020-08-04-mother-of-mothers.html"
               "The Mothers of the Mother of All Demos"
               "Bill English was part of a team that revolutionized computing. A look at the shoulders they stood on."
               "August 04, 2020"
               "#sts, #informatics")
    (post-item "https://schmud.de/posts/2020-06-02-mlk.html"
               "MLK and \u201cDomestic Terrorism\u201d"
               "The narrative can change, but we must overcome the disinformation and fear that has overtaken the United States."
               "June 02, 2020"
               "#sts, #suchness")
    (post-item "https://schmud.de/posts/2021-09-07-fix-my-code.html"
               "Fix My Code"
               "Engineering alone can\u2019t fix what\u2019s wrong with the internet."
               "September 07, 2021"
               "#sts, #suchness, #review")]
   [:p "Expect four to six posts a year. Lesser quantity \u2192 higher quality."]
   [:p [:small "eMail subscriptions are beholden to Kit\u2019s "
        [:a {:href "https://kit.com/terms"} "Terms of Service"] "."]]])

;; ---------------------------------------------------------------------------
;; Renderer
;; ---------------------------------------------------------------------------

(defn render [{global-meta :meta}]
  (let [page {:title         "Subscribe to Beyond the Frame"
              :description   "The various ways to stay up to date with Beyond the Frame"
              :canonical-url (str (:base-url global-meta) "pages/feeds.html")}]
    (body-template
     global-meta
     page
     [:article
      [:h1.btf-font "Subscribe to Beyond the Frame"]
      vmail-figure
      email-form
      web-section
      greatest-hits])))
