(ns day-12-2
  (:require [day-12-input :as input]))

(defn rotate [[x y] deg]
  (reduce (fn [[x y] _]
            [(- y) x])
          [x y]
          (range (/ deg 90))))

(defn step [[[posx posy][wx wy]] [action n]]
  (case action
    :F [[(+ posx (* wx n))(+ posy (* wy n))] [wx wy]]
    :L [[posx posy] (rotate [wx wy] (- 360 n))]
    :R [[posx posy] (rotate [wx wy] (+ n))]
    :N [[posx posy] [wx (- wy n)]]
    :E [[posx posy] [(+ wx n) wy]]
    :S [[posx posy] [wx (+ wy n)]]
    :W [[posx posy] [(- wx n) wy]]))

(let [[[posx posy] _] (reduce step [[0 0] [10 -1]] input/day-12-input)]
  (+ (Math/abs posx) (Math/abs posy)))
;; => 52742

