(defproject alarm-server "0.1.0-SNAPSHOT"
  :description "Server for om-alarming."
  :url "http://seasoft.com"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.async "0.2.374"]
                 [com.taoensso/sente "1.8.0"]               ; <--- Sente
                 [com.taoensso/timbre "4.3.0"]
                 [http-kit "2.1.21-alpha2"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]               ; Includes `ring-anti-forgery`, etc.
                 [compojure "1.4.0"]                        ; Or routing lib of your choice
                 [com.cognitect/transit-clj "0.8.285"]
                 [com.cognitect/transit-cljs "0.8.237"]
                 [enhanced-smartgas-deps "1.0.0"]
                 ]

  :plugins
  [[lein-pprint "1.1.2"]
   [lein-ancient "0.6.8"]
   [lein-cljsbuild "1.1.2"]
   ]

  ;; This won't help b/c it is the default. Sometimes need to manually delete resources/public/main.js
  :clean-targets [:target-path "target"]

  :java-source-paths ["java/src"]

  ;; Already merging, and there's no way it could do akka specific merging (and thus tested - it did not)
  ;;:pom-plugins [[org.apache.maven.plugins/maven-shade-plugin 2.2]]

  :profiles {:uberjar {:aot :all}}

  :cljsbuild
  {:builds
   [{:id           :cljs-client
     :source-paths ["src"]
     :compiler     {:output-to     "resources/public/main.js"
                    :optimizations :whitespace #_:advanced
                    :parallel-build true
                    :pretty-print  true}}]}

  :aot [alarm-server.core]
  :main alarm-server.core

  :aliases
  {"start-repl" ["do" "cljsbuild" "once," "repl" ":headless"]
   "start"      ["do" "cljsbuild" "once," "run"]
   "uber"       ["do" "clean," "cljsbuild" "once," "uberjar"]
   }

  :target-path "target"
  :uberjar-name "alarm-server.jar"
  :omit-source true
  :repositories [["localrepo1" {;:url "file:///C:/dev/alarm-server"
                                :url "file:///home/chris/IdeaProjects/alarm-server"
                                :username :env/localrepo_username
                                :password :env/localrepo_password}]]
  )
