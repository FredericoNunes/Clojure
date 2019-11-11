(ns financeiro.handler-test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]
            [financeiro.handler :refer :all] ;;name space que será testado se insere assim
            [financeiro.db :as db])) ;; referencia ao namespace db 


(facts "Hello World na pasta raiz"
       (fact "o status da resposta é 200"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200));; =>  quando a função da esquerda for aplicada o resultado esperado é o valor a direita

       (fact "o test do corpo é 'Olá Mundo'"
             (let [response (app (mock/request :get "/"))]
               (:body response) => "Hello World"))

       (fact "Nova Rota Esta Ok"
             (let [response (app (mock/request :get "/novaRota"))]
                (:status response) => 200)))


(facts "Rota inválida não existe"
       (fact "o codigo de erro é 404"
             (let [response (app (mock/request :get "/invalid"))]
               (:status response) => 404 )))
;; da pra simplificar o mock, resolvi deixar assim pra entender melhor



(facts "Saldo Inicial é 0" :aceitacao ;;rotulo para o midje enxergar
  (against-background [(json/generate-string {:saldo 0})
   => "{\"saldo\":0}"
   (db/saldo) => 0 ])
  (let [response (app (mock/request :get "/saldo"))]
    (fact "o formato é 'application/json'"
      (get-in response [:headers "Content-Type"])
        => "application/json; charset=utf-8")

    (fact "o status da resposta é 200"
      (:status response) => 200)
    (fact "o texto do corpo é '0'"
      (:body response) => "{\"saldo\":0}")
      )
) ;; esse aqui já é o simplificado



(facts "Regsitrar uma Receita no valor de 10"
    ;; cria um mock para a funcao db/registrar
    (against-background (db/registrar {:valor 10 
                                      :tipo "receita"})
                          => {:id 1 :valor 10 :tipo "receita"}
    )
    (let [response 
      (app 
        (-> 
          (mock/request :post "/transacoes")
            ;; criar o conteudo do Post
          (mock/json-body 
              {:valor 10
               :tipo "receita"}
          )
        )
      )]
        (fact "o status da resposta é 201"
          (:status response) => 201)
                    

        (fact "o texto do corpo é um Json com o conteudo enviado e um id"
          (:body response) => "{\"id\":1,\"valor\":10,\"tipo\":\"receita\"}"
                            )))




