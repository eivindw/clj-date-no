(ns date-no-test
  (:require [clojure.test :refer :all]
            [date-no :refer :all])
  (:use [clojure.set :only [difference]]))

(deftest check-holiday
  (testing "Check specific known holidays"
    (is (holiday? (date 2016 3 28)))
    (is (holiday? (date 2020 5 21)))))

(deftest check-working-day
  (testing "Check specific known working days"
    (is (working-day? (date 2016 4 4)))
    (is (not (working-day? (date 2016 4 3))))))

(deftest find-holidays-2016
  (testing "Find all holidays for 2016"
    (let [year 2016
          holidays (find-holidays year)
          expected-days #{(date year 1 1)
                          (date year 3 20)
                          (date year 3 24)
                          (date year 3 25)
                          (date year 3 27)
                          (date year 3 28)
                          (date year 5 1)
                          (date year 5 5)
                          (date year 5 15)
                          (date year 5 16)
                          (date year 5 17)
                          (date year 12 25)
                          (date year 12 26)}]
      (is (empty? (difference holidays expected-days)))
      (is (empty? (difference expected-days holidays))))))

(deftest count-holidays-2027
  (testing "Count days in year 2027 with one less day"
    (is (= 5 (count (find-set-holidays 2027))))
    (is (= 8 (count (find-variable-holidays 2027))))
    (is (= 12 (count (find-holidays 2027))))))

(deftest add-working-days-to-date
  (testing "Adding working days to given date"
    (is (= (date 2016 4 11) (add-working-days (date 2016 4 4) 5)))
    (is (= (date 2016 3 31) (add-working-days (date 2016 3 21) 5)))))
