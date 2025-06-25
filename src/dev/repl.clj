(ns repl
  (:require
   [shadow.cljs.devtools.api :as shadow]
   [acme.server :as srv]
   [ring.adapter.jetty :as jetty]))

(defonce jetty-ref (atom nil))

(defn start []
  (shadow/watch :frontend)
  
  (reset! jetty-ref
          (jetty/run-jetty #'srv/handler
                           {:port 3000
                            :join? false}))
  ::started)

(defn stop []
  (when-some [jetty @jetty-ref]
    (reset! jetty-ref nil)
    (.stop jetty))
  ::stopped)

(defn go []
  (stop)
  (start))
