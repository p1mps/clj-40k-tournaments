(ns db
  (:require coast))

(defn find-row [table id]
  (first (coast/q [(str "select * from " table " where id = ?") id])))
