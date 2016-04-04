(ns date-no-test
  (:require [clojure.test :refer :all]
            [date-no :refer :all])
  (:use [java-time :only [local-date] :rename {local-date ld}]
        [clojure.set :only [difference]]))

(deftest check-holiday
  (testing "Check specific known holidays"
    (is (holiday? (ld 2016 3 28)))
    (is (holiday? (ld 2020 5 21)))))

(deftest check-working-day
  (testing "Check specific known working days"
    (is (working-day? (ld 2016 4 4)))
    (is (not (working-day? (ld 2016 4 3))))))

(deftest find-holidays-2016
  (testing "Find all holidays for 2016"
    (let [year 2016
          holidays (find-holidays year)
          expected-days #{(ld year 1 1)
                          (ld year 3 20)
                          (ld year 3 24)
                          (ld year 3 25)
                          (ld year 3 27)
                          (ld year 3 28)
                          (ld year 5 1)
                          (ld year 5 5)
                          (ld year 5 15)
                          (ld year 5 16)
                          (ld year 5 17)
                          (ld year 12 25)
                          (ld year 12 26)}]
      (is (empty? (difference holidays expected-days)))
      (is (empty? (difference expected-days holidays))))))
