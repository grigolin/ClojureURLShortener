(ns url-shortener.config
  (:require [url-shortener.env :as env]
            [taoensso.carmine :as car :refer [wcar]]
            [clojure.string :as str]
            [clojure.data.codec.base64 :as b64]))

;; redis config
(defonce my-conn-pool (car/connection-pool {}))

(def my-conn-spec-1 {:uri env/redis-url})

(def my-wcar-opts
  {:pool my-conn-pool
   :spec my-conn-spec-1})

(defn save-to-redis
  [key value]
  (wcar my-wcar-opts (car/set key value)))

(defn get-from-redis
  [key]
  (wcar my-wcar-opts (car/get key)))

;; hash id
(defn gen-id []
  (rand-int 100000000))

(defn hash-id [n]
  (-> (str n)
      .getBytes
      b64/encode
      (String.)
      (str/replace "+" "-")
      (str/replace "/" "_")
      (str/replace "=" "")))

;; usecases
(defn hash-url-and-save [url]
  (let [hash-key (hash-id (gen-id))]
    (save-to-redis hash-key url)
    hash-key))

(defn get-original-url [hash-key]
  (get-from-redis hash-key))
