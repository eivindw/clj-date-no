(defproject clj-date-no "0.2.0-SNAPSHOT"
  :description "Library calculating holidays and workdays for Norway."
  :url "https://github.com/eivindw/clj-date-no"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [easter-day "0.1.1"]]
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :repl-options {:init-ns date-no})
