(ns financeiro.saldo-aceitacao-test
    (:require [midje.sweet :refer :all] ;; referencia ao midje
              [financeiro.handler :refer [app]]
              [ring.adapter.jetty :refer [run-jetty]]
              [clj-http.client :as http]
              )) ;; pra dizer ao jetty quais as rotas disponiveis

(def servidor (atom nil)) ;; atomo qua guarda a referência da instancia jetty

(defn iniciar-servidor [porta]
    (swap! servidor ;; swap espera uma função como argumento
        (fn [_] (run-jetty app {:port porta :join? false}) ;; run-jetty espera as rotas

        )
    )
)

(defn parar-servidor []
    (.stop @servidor)
)

;; fato para verificar a manipulação do servidor
(fact "saldo inicial é 0"
    (iniciar-servidor 3001)
        (:body (http/get "http://localhost:3001/saldo")) => "0"
    (parar-servidor)
    )