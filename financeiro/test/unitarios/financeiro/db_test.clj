(ns financeiro.db-test
(:require [midje.sweet :refer :all]
          [financeiro.db :refer :all]))


(facts "Guarda uma transação num atomo"
    (against-background [(before :facts (limpar))]
        (fact "a coleção de transações inicia vazia"
            (count (transacoes)) => 0)

        (fact "a transação é o primeiro registro"
            (registrar {:valor 7 :tipo "receita"})
            => {:id 1 :valor 7 :tipo "receita"}
            (count (transacoes)) => 1)))

(facts "calcula o saldo dada um colecao de transacoes"
    (against-background [(before :facts (limpar))]
        (fact "saldo é positivo quando só tem receita"
            (registrar {:valor 1 :tipo "receita"})
            (registrar {:valor 10 :tipo "receita"})
            (registrar {:valor 100 :tipo "receita"})
            (registrar {:valor 1000 :tipo "receita"})
            (saldo) => 1111)
            
         (fact "saldo é positivo quando só tem receita"
            (registrar {:valor 2    :tipo "despesa"})
            (registrar {:valor 20   :tipo "despesa"})
            (registrar {:valor 200  :tipo "despesa"})
            (registrar {:valor 2000 :tipo "despesa"})
            (saldo) => -2222)

        (fact "saldo é a soma das receitas menos as despesas"
            (registrar {:valor 2    :tipo "despesa"})
            (registrar {:valor 10   :tipo "receita"})
            (registrar {:valor 200  :tipo "despesa"})
            (registrar {:valor 1000 :tipo "receita"})
            (saldo) => 808)
            ))