(ns financeiro.saldo-aceitacao-test
    (:require [midje.sweet :refer :all] ;; referencia ao midje
              [cheshire.core :as json]
              [financeiro.auxiliares :refer :all]
              [financeiro.db :as db]
              [clj-http.client :as http]
              )) ;; pra dizer ao jetty quais as rotas disponiveis


(against-background [(before :facts [(iniciar-servidor 
                                        porta-padrao)
                                        (db/limpar)])
                    (after :facts (parar-servidor))]

    (fact "saldo inicial é 0" :aceitacao
        (json/parse-string (conteudo "/saldo") true) => {:saldo 0})
    
    
    (fact "O saldo é 10 quando a única transação é uma receita de 10" :aceitacao
    ;;requisicao tipo post para a rota /transacoes
    ;; json {"valor" 10, "tipo" "receita"}
        (http/post (endereco-para "/transacoes")
            {:body (json/generate-string {:valor 10 :tipo "receita"})
             :content-type "application/json; charset=utf-8"})
        (json/parse-string (conteudo "/saldo") true) => {:saldo 10}
    )

    (fact "o saldo é 1000 quando criamos duas receitas de 2000
            e uma despesa de 3000" :aceitacao
        (http/post (endereco-para "/transacoes")
                {:content-type "application/json; charset=utf-8"
                 :body (json/generate-string {:valor 2000
                                              :tipo "receita"})})
            
        (http/post (endereco-para "/transacoes")
                {:content-type "application/json; charset=utf-8"
                 :body (json/generate-string {:valor 2000
                                              :tipo "receita"})})

        (http/post (endereco-para "/transacoes")
                {:content-type "application/json; charset=utf-8"
                 :body (json/generate-string {:valor 3000
                                              :tipo "despesa"})})
                            
        (json/parse-string (conteudo "/saldo") true ) => {:saldo 1000}

    )

)

