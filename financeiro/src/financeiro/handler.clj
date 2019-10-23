(ns financeiro.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/novaRota" [] "Minha Rota")
  (GET "/saldo" [] "0")
  (route/not-found "Not Found")
  )

;;defroutes é uma importação de compojure.core 
;; diz ao progreama o que fazer com a url 





(def app
  (wrap-defaults app-routes site-defaults))


;;wrap-defaults um função
;;site-dafaults um mapa para configurações para API HTTP

;;posso usar o defroutes outras vezes, e usar outro nome para app-routes



;;lein ring server

;;wrap-defaults é um função que pega o app-routes e define as condigurações do site-defalts


