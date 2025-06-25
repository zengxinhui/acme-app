(ns acme.frontend.app
  (:require [clojure.string :as str]
            [reagent.core :as r]
            [reagent.dom.client :as rdomc]))

(defonce timer (r/atom (js/Date.)))

(defonce time-updater (js/setInterval
                       #(reset! timer (js/Date.)) 1000))

(defn root []
  (println "Hello World")
  [:div "Hello World! The time now is " (-> @timer .toTimeString (str/split " ") first)])

(defonce react-root (delay (rdomc/create-root (.getElementById js/document "root"))))

(defn ^:export init []
  (rdomc/render @react-root [root]))
