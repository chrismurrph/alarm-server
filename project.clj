(defproject alarm-server "0.1.0-SNAPSHOT"
  :description "Server for om-alarming. Intended to not work unless you have the right jar files"
  :url "http://seasoft.com"
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
                 [local/Developer "1.0"]
                 [local/JEasyResources "1.0"]
                 [local/JEasyWeb "1.0"]
                 [local/RXTXcomm "1.0"]
                 [local/SC-Customer "1.0"]
                 [local/SC-HiCommon "1.0"]
                 [local/SC-MyRegisterDriver "1.0"]
                 [local/SC-Server "1.0"]
                 [local/akka-actor_2.11-2.3.9 "1.0"]
                 [local/akka-remote_2.11-2.3.9 "1.0"]
                 [local/aopalliance "1.0"]
                 [local/ashwood-2.0 "1.0"]
                 [local/at-common_2.11 "1.0"]
                 [local/cayenne-server-3.0 "1.0"]
                 [local/commons-collections-3.1 "1.0"]
                 [local/commons-logging-1.1.3 "1.0"]
                 [local/config-1.2.1 "1.0"]
                 [local/joda-time-2.4 "1.0"]
                 [local/log4j-1.2.16 "1.0"]
                 [local/mysql-connector-java-3.0.15-ga-bin "1.0"]
                 [local/netty-3.9.8.Final "1.0"]
                 [local/opiOpcIO "1.0"]
                 [local/points "1.0"]
                 [local/protobuf-java-2.5.0 "1.0"]
                 [local/quartz-1.8.3 "1.0"]
                 [local/scala-library-2.11.6 "1.0"]
                 [local/seroUtils "1.0"]
                 [local/slf4j-api-1.6.1 "1.0"]
                 [local/slf4j-log4j12-1.6.1 "1.0"]
                 [local/spring-aop-3.2.8.RELEASE "1.0"]
                 [local/spring-beans-3.2.8.RELEASE "1.0"]
                 [local/spring-context-3.2.8.RELEASE "1.0"]
                 [local/spring-core-3.2.8.RELEASE "1.0"]
                 [local/spring-expression-3.2.8.RELEASE "1.0"]
                 [local/spring-jdbc-3.2.8.RELEASE "1.0"]
                 [local/spring-security-config-3.2.5.RELEASE "1.0"]
                 [local/spring-security-core-3.2.5.RELEASE "1.0"]
                 [local/spring-security-remoting-3.2.5.RELEASE "1.0"]
                 [local/spring-security-web-3.2.5.RELEASE "1.0"]
                 [local/spring-tx-3.2.8.RELEASE "1.0"]
                 [local/spring-web-3.2.8.RELEASE "1.0"]
                 [local/spring-webmvc-3.2.8.RELEASE "1.0"]
                 [local/velocity-1.3 "1.0"]
                 ]

  :plugins
  [[lein-pprint "1.1.2"]
   [lein-ancient "0.6.8"]
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

  :aot [alarm-server.core]
  :main alarm-server.core

  :aliases
  {"start-repl" ["do" "cljsbuild" "once," "repl" ":headless"]
   "start"      ["do" "cljsbuild" "once," "run"]
   "uber"       ["do" "clean," "cljsbuild" "once," "uberjar"]
   }

  :target-path "target"
  ;:jar-name "alarm-server.jar"
  :uberjar-name "alarm-server.jar"
  :omit-source true
  ;:manifest {"Class-Path"
  ;           "../lib/points.jar
  ;            ../lib/clojure-1.8.0.jar
  ;            ../lib/at-common_2.11.jar
  ;            ../lib/SC-HiCommon.jar
  ;            ../lib/SC-Server.jar
  ;            ../lib/Developer.jar
  ;            ../lib/JEasyResources.jar
  ;            ../lib/JEasyWeb.jar
  ;            ../lib/RXTXcomm.jar
  ;            ../lib/SC-Customer.jar
  ;            ../lib/SC-HiCommon.jar
  ;            ../lib/SC-MyRegisterDriver.jar
  ;            ../lib/SC-Server.jar
  ;            ../lib/akka-actor_2.11-2.3.9.jar
  ;            ../lib/akka-remote_2.11-2.3.9.jar
  ;            ../lib/aopalliance.jar
  ;            ../lib/ashwood-2.0.jar
  ;            ../lib/at-common_2.11.jar
  ;            ../lib/cayenne-server-3.0.jar
  ;            ../lib/commons-collections-3.1.jar
  ;            ../lib/commons-logging-1.1.3.jar
  ;            ../lib/config-1.2.1.jar
  ;            ../lib/joda-time-2.4.jar
  ;            ../lib/log4j-1.2.16.jar
  ;            ../lib/mysql-connector-java-3.0.15-ga-bin.jar
  ;            ../lib/netty-3.9.8.Final.jar
  ;            ../lib/opiOpcIO.jar
  ;            ../lib/points.jar
  ;            ../lib/protobuf-java-2.5.0.jar
  ;            ../lib/quartz-1.8.3.jar
  ;            ../lib/scala-library-2.11.6.jar
  ;            ../lib/seroUtils.jar
  ;            ../lib/slf4j-api-1.6.1.jar
  ;            ../lib/slf4j-log4j12-1.6.1.jar
  ;            ../lib/spring-aop-3.2.8.RELEASE.jar
  ;            ../lib/spring-beans-3.2.8.RELEASE.jar
  ;            ../lib/spring-context-3.2.8.RELEASE.jar
  ;            ../lib/spring-core-3.2.8.RELEASE.jar
  ;            ../lib/spring-expression-3.2.8.RELEASE.jar
  ;            ../lib/spring-jdbc-3.2.8.RELEASE.jar
  ;            ../lib/spring-security-config-3.2.5.RELEASE.jar
  ;            ../lib/spring-security-core-3.2.5.RELEASE.jar
  ;            ../lib/spring-security-remoting-3.2.5.RELEASE.jar
  ;            ../lib/spring-security-web-3.2.5.RELEASE.jar
  ;            ../lib/spring-tx-3.2.8.RELEASE.jar
  ;            ../lib/spring-web-3.2.8.RELEASE.jar
  ;            ../lib/spring-webmvc-3.2.8.RELEASE.jar
  ;            ../lib/velocity-1.3.jar
  ;            ../lib/commons-codec-1.10.jar"
  ;            }

  :repositories [["localrepo1" {;:url "file:///C:/dev/alarm-server"
                                :url "file:///home/chris/IdeaProjects/alarm-server"
                                :username :env/localrepo_username
                                :password :env/localrepo_password}]]
  ;:repositories
  ;{"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  )
