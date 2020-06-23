(ns site.cv
  (:require [clojure.edn :as edn]))

(def recognition (-> "src/site/recognition.edn" slurp edn/read-string))

(def projects (-> "src/site/projects.edn" slurp edn/read-string))

(def talks-workshops (-> "src/site/talks-workshops.edn" slurp edn/read-string))

(comment

  (let [{:keys [exhibitions honors-grants-awards affiliations education training]} recognition] (first honors-grants-awards))

  (let [{:keys [workshops conference-talks]} talks-workshops] (first conference-talks))
  (keys projects)
  (get-in projects [:jack-and-the-machine :title])

  )
