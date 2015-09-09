(defproject jarohen/yoyo.jdbc-pool "0.0.4-SNAPSHOT"
  :description "A module to set up a JDBC connection pool in Yo-yo"
  :url "https://github.com/james-henderson/yoyo"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jarohen/yoyo "0.0.6-SNAPSHOT"]
                 [org.clojure/tools.logging "0.3.1"]

                 [org.clojure/java.jdbc "0.3.5"]
                 [org.apache.commons/commons-dbcp2 "2.0.1"]])
