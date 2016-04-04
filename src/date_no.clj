(ns date-no
  (:use [clojure.set :only [union]]
        [easter-day])
  (:import (java.time LocalDate DayOfWeek)))

(defn ld [year month day]
  (LocalDate/of year month day))

(defn find-set-holidays [year]
  #{(ld year 1 1)
    (ld year 5 1)
    (ld year 5 17)
    (ld year 12 25)
    (ld year 12 26)})

(defn find-variable-holidays [year]
  (let [esMap (easter-sunday year)
        eDay (ld year (:month esMap) (:day esMap))]
    #{(.minusDays eDay 7)
      (.minusDays eDay 3)
      (.minusDays eDay 2)
      eDay
      (.plusDays eDay 1)
      (.plusDays eDay 39)
      (.plusDays eDay 49)
      (.plusDays eDay 50)}))

(defn find-holidays [year]
  (union (find-set-holidays year) (find-variable-holidays year)))

(defn holiday? [date]
  (contains? (find-holidays (.getYear date)) date))

(defn weekend? [date]
  (let [dayOfWeek (.getDayOfWeek date)]
    (or (= dayOfWeek DayOfWeek/SATURDAY)
        (= dayOfWeek DayOfWeek/SUNDAY))))

(defn working-day? [date]
  (not (or (weekend? date) (holiday? date))))

(defn working-days [from]
  (filter working-day? (iterate #(.plusDays % 1) from)))

(defn add-working-days [date no-days]
  (nth (working-days date) no-days))
