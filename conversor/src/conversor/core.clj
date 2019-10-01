(ns conversor.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))


(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "eur"]
   ["-p" "--para moeda destino" "moeda a qual queremos saber o valor"]])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (prn "as opcoes são:" (:options 
                            (parse-opts args opcoes-do-programa))))
