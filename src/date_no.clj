(ns date-no
  (:use [java-time :only [local-date minus plus days as weekend?] :rename {local-date ld}]
        [easter-day :rename {easter-sunday es}]))

(defn find-holidays
  "Find all holidays for the given year"
  [year]
  (let [esMap (es year)
        eDay (ld year (:month esMap) (:day esMap))]
    #{(ld year 1 1)
      (ld year 5 1)
      (ld year 5 17)
      (ld year 12 25)
      (ld year 12 26)
      (minus eDay (days 7))
      (minus eDay (days 3))
      (minus eDay (days 2))
      eDay
      (plus eDay (days 1))
      (plus eDay (days 39))
      (plus eDay (days 49))
      (plus eDay (days 50))}))

(defn holiday? [date]
  (contains? (find-holidays (as date :year)) date))

(defn working-day? [date]
  (not (or (weekend? date) (holiday? date))))
