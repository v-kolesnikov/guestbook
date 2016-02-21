(ns guestbook.routes.home
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]
            [compojure.core :refer [GET POST defroutes]]
            [guestbook.db.core :as db]
            [guestbook.layout :as layout]
            [ring.util.response :refer [redirect]]))

(defn validate-message [params]
  (first
   (b/validate
    params
    :name v/required
    :message [v/required [v/min-count 10]])))

(defn save-message!
  [{:keys [params]}]
  (if-let [errors (validate-message params)]
    (-> (redirect "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/save-message!
       (assoc params :timestamp (java.util.Date.)))
      (redirect "/"))))

(defn home-page [{:keys [flash]}]
  (layout/render
   "home.html"
   (merge {:messages (db/get-messages)}
          (select-keys flash [:name :message :errors]))))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (save-message! request))
  (GET "/about" [] (about-page)))
