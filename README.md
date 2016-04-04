# clj-date-no

A Clojure library providing functions to calculate Norwegian holidays and working days.

## Usage

Get all holidays for a given year, as a set of `java.time.LocalDate` values:
```clojure
; Find all holidays for 2016
(def holidays-2016 (find-holidays 2016))

; Is today a holiday?
(holiday? (java.time.LocalDate/now))

; Is today a working day?
(working-day? (java.time.LocalDate/now))

; Add 10 working days (2 weeks) to date - skipping weekends and holidays
(add-working-days (java.time.localDate/now) 10)
```

## License

Copyright © 2016 Eivind Waaler

Distributed under the Eclipse Public License either version 1.0 or any later version.
