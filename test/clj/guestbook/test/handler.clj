(ns guestbook.test.handler
  (:require [clojure.test :refer :all]
            [guestbook.db.migrations :as migrations]
            [guestbook.handler :refer :all]
            [ring.mock.request :refer :all]))

(use-fixtures
  :once
  (fn [f]
    (migrations/migrate ["migrate"])
    (f)))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))
