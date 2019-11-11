(ns financeiro.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [financeiro.db :as db]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn como-json [conteudo & [status]]
   {:status (or status 200)
    :headers {"Content-Type"
            "application/json; charset=utf-8"}
     :body (json/generate-string conteudo)})


(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/novaRota" [] "Minha Rota")
  (GET "/saldo" [] (como-json {:saldo (db/saldo)}))
  (POST "/transacoes" requisicao (-> (db/registrar (:body requisicao))
                                     (como-json 201)))
  (route/not-found "Not Found")
)

;; defroutes é uma importação de compojure.core
;; 
;; diz ao progreama o que fazer com a url 





(def app
  (->
    (wrap-defaults app-routes api-defaults)
    (wrap-json-body {:keywords? true :bigdecimals? true})
  )
)


;;wrap-defaults um função
;;site-dafaults um mapa para configurações para API HTTP

;;posso usar o defroutes outras vezes, e usar outro nome para app-routes



;;lein ring server

;;wrap-defaults é um função que pega o app-routes e define as condigurações do site-defalts


