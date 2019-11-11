(defproject financeiro "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [cheshire "5.8.1"] ;;lidar com json
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [clj-http "3.9.1"]] ;; habilita fazer requisições http
  :plugins [[lein-ring "0.12.5"]] ;; Avalia quanto do código está testado
  :ring {:handler financeiro.handler/app} ;; nessle projeto nao tem main por isso precisa dizer ao ring pra quem delegar as requicoes HTTP
  :profiles
        {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [midje "1.9.6"] ;;testes por facts
                        [ring/ring-core "1.7.1"] ;; uma dependencia do ring-jetty-adapter
                        [ring/ring-jetty-adapter "1.7.1"] ;; biblioteca para iniciar e parar o servidor
                        ] 

         :plugins [[lein-midje "3.2.1"]
                   [lein-cloverage "1.1.2"]
                    ]}}
  :test-paths ["test/unitarios" "test/aceitacao"])


;;lein ring server

;;lein midje :autotest

;;lein cloveraje --runner :midje