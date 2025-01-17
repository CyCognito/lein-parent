(defproject lein-parent "0.3.9-SNAPSHOT"
  :description "Leiningen plugin for inheriting properties from a parent project"
  :url "https://github.com/achin/lein-parent"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :plugins [[lein-midje "3.2.1"]]
  :profiles {:dev {:dependencies [[midje "1.9.8"]
                                  [fipp "0.6.24"] ;; solve a conflict
                                  [com.cemerick/pomegranate "1.1.0"]]}})
