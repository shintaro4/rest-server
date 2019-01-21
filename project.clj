(defproject rest-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [duct/core "0.7.0"]
                 [duct/module.logging "0.4.0"]
                 [duct/module.web "0.7.0"]
                 [duct/module.ataraxy "0.3.0"]
                 [duct/module.sql "0.5.0"]
                 [org.postgresql/postgresql "42.2.5"]
                 [camel-snake-kebak "0.4.0"]
                 [honeysql "0.9.1"]]
  :plugins [[duct/lein-duct "0.11.2"]]
  :main ^:skip-aot rest-server.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :middleware     [lein-duct.plugin/middleware]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.3.1"]
                                   [eftest "0.5.4"]
                                   [kerodon "0.9.0"]
                                   [com.gearswithingears/shurubbery "0.4.1"]]
                  :plugins [[jonase/eastwood "0.2.5"]
                            [lein-eftest "0.4.1"]
                            [lein-cljfmt "0.5.7"]
                            [lein-kibit "0.1.6"]]
                  :aliases {"lint" ^{:doc "Execute cljfmt check, eastwood and kibit."}
                            ["do" ["cljfmt" "check"] ["eastwood" "{:source-paths [\"src\"]}"] ["kibit"]]}}})
