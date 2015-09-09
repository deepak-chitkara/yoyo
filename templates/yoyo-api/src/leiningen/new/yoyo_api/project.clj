(defproject {{name}} ""

  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [jarohen/embed-nrepl "0.1.1"]

                 [jarohen/yoyo "0.0.3"]

                 [ring/ring-core "1.3.0"]
                 [jarohen/yoyo.aleph "0.0.1"]
                 [bidi "1.19.0"]
                 [ring-middleware-format "0.5.0" :exclusions [ring]]

                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-api "1.7.9"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.1"]
                 [org.apache.logging.log4j/log4j-core "2.1"]]

  :exclusions [org.clojure/clojure]

  :aliases {"dev" ["run" "-m" "{{name}}.service.main"]
            "build" "uberjar"})
