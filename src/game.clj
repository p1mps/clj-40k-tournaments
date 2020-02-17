(ns game
  (:require coast))

(defn players [game]
  (let [user (first (coast/q [:select '* :from 'user
                              :where [:id (:game/user game)]]))
        user-2  (first (coast/q [:select '* :from 'user
                                 :where [:id (:game/user-2 game)]]))]
    {:player1 user
     :player2 user-2}))
