(ns date-no
  (:use [clojure.set :only [union]]
        [java-time :only [local-date minus plus days as weekend?] :rename {local-date ld}]
        [easter-day]))

(defn find-set-holidays [year]
  #{(ld year 1 1)
    (ld year 5 1)
    (ld year 5 17)
    (ld year 12 25)
    (ld year 12 26)})

(defn find-variable-holidays [year]
  (let [esMap (easter-sunday year)
        eDay (ld year (:month esMap) (:day esMap))]
    #{(minus eDay (days 7))
      (minus eDay (days 3))
      (minus eDay (days 2))
      eDay
      (plus eDay (days 1))
      (plus eDay (days 39))
      (plus eDay (days 49))
      (plus eDay (days 50))}))

(defn find-holidays
  "Find all holidays for the given year"
  [year]
  (union (find-set-holidays year) (find-variable-holidays year)))

(defn holiday? [date]
  (contains? (find-holidays (as date :year)) date))

(defn working-day? [date]
  (not (or (weekend? date) (holiday? date))))

(defn working-days [from]
  (filter working-day? (iterate #(plus % (days 1)) from)))

(defn add-working-days [date no-days]
  (nth (working-days date) no-days))
