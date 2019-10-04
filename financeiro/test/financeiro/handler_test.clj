(ns financeiro.handler-test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [financeiro.handler :refer :all]))

(facts "Hello World na pasta raiz"
       (fact "o status da resposta é 200"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200))
       
       (fact "o test do corpo é 'Hello World'"
             (let [response (app (mock/request :get "/"))]
               (:body response) => "Hello World"))

       (fact "Nova Rota Esta Ok"
             (let [response (app (mock/request :get "/novaRota"))]
                (:status response) => 200)))

(facts "Rota inválida não existe"
       (fact "o codigo de erro é 404"
             (let [response (app (mock/request :get "/invalid"))]
               (:status response) => 404 )))
