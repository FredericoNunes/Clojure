(ns financeiro.handler-test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [financeiro.handler :refer :all])) ;;name space que será testado se insere assim

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

(facts "Saldo Inicial é 0" :aceitacao ;;rotulo para o midje enchergar
  (let [response (app (mock/request :get "/saldo"))]
    (fact "o status da resposta é 200"
      (:status response) => 200)
    (fact "o texto do corpo é '0'"
      (:body response) => "0")
      )
)

;; esse aqui já é o simplificado
