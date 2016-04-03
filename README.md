# clj-date-no

A Clojure library providing functions to calculate Norwegian holidays and working days.

## Usage

Get all holidays for a given year, as a set of `java.time.LocalDate` values:
```clojure
(def holidays (find-holidays 2016))
```

## License

Copyright © 2016 Eivind Waaler

Distributed under the Eclipse Public License either version 1.0 or any later version.
