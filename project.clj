(defproject alarm-server "0.1.0-SNAPSHOT"
  :description "Server for om-alarming. Intended to not work unless you have the right jar files"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]
                 [com.taoensso/sente "1.8.0"]               ; <--- Sente
                 [com.taoensso/timbre "4.3.0"]
                 [http-kit "2.1.21-alpha2"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]               ; Includes `ring-anti-forgery`, etc.
                 [compojure "1.4.0"]                        ; Or routing lib of your choice
                 [com.cognitect/transit-clj "0.8.285"]
                 [com.cognitect/transit-cljs "0.8.237"]
                 ]

  :plugins
  [[lein-pprint "1.1.2"]
   ;[lein-ancient "0.6.8"]
   ;[com.cemerick/austin "0.1.6"]
   [lein-cljsbuild "1.1.2"]
   ]

  :profiles {:uberjar {:aot :all}}

  :cljsbuild
  {:builds
   [{:id           :cljs-client
     :source-paths ["src"]
     :compiler     {:output-to     "resources/public/main.js"
                    :optimizations :whitespace #_:advanced
                    :pretty-print  true}}]}

  ;:aot [alarm-server.core]
  :main alarm-server.core

  :aliases
  {"start-repl" ["do" "cljsbuild" "once," "repl" ":headless"]
   "start"      ["do" "cljsbuild" "once," "run"]
   "uber"      ["do" "clean," "cljsbuild" "once," "uberjar," "run"]}

  ;:target-path "target/%s"
  :uberjar-name "alarm-server.jar"
  :omit-source true
  :manifest {"Class-Path" "../lib/upper.jar"}

  ;:repositories
  ;{"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  )
