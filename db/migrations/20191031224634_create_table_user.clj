(ns migrations.20191031224634-create-table-user
  (:require [coast.db.migrations :refer :all]))

(defn change []
  (create-table :user
    (text :email)
    (text :password)
    (timestamps)))