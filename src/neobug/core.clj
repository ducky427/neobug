(ns neobug.core
  (:require
    [clojurewerkz.neocons.rest :as nr]
    [clojurewerkz.neocons.rest.nodes :as nn]
    [clojurewerkz.neocons.rest.relationships :as nrl]
    [clojurewerkz.neocons.rest.cypher :as cy]
    [clojurewerkz.neocons.rest.labels :as nl])
  (:gen-class))

(def server (atom "http://127.0.0.1:7474/db/data/"))

(defn init
  [url]
  (reset! server url))

(defn- connect
  "Create connection to specified database."
  []
  (nr/connect @server))

(defn node
  "Get a node by label and name"
  ([con label nname]
   (get (first (cy/tquery con (str "MATCH (n:" label ") where n.name={name} RETURN n") {:name nname})) "n"))
  ([label nname]
   (node (connect) label nname)))

(defn user
  "Get a user by name."
  ([con username]
   (:data (node con "user" username)))
  ([username]
   (:data (node "user" username))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
