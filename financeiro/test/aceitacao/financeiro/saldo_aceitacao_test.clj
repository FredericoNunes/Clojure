(ns financeiro.saldo-aceitacao-test
    (:require [midje.sweet :refer :all] ;; referencia ao midje
              [financeiro.auxiliares :refer :all]
              )) ;; pra dizer ao jetty quais as rotas disponiveis


(against-background [(before :facts (iniciar-servidor 
                                        porta-padrao))
                    (after :facts (parar-servidor))]

    (fact "saldo inicial é 0" :aceitacao
        (conteudo "/saldo") => "0"))

