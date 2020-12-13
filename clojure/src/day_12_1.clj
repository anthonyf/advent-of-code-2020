(ns day-12-1
  (:require [day-12-input :as input]))

(defn rotate [[x y] deg]
  (reduce (fn [[x y] _]
            [(- y) x])
          [x y]
          (range (/ deg 90))))

(defn step [[[posx posy] [dirx diry]] [action n]]
  (case action
    :F [[(+ posx (* n dirx)) (+ posy (* n diry))] [dirx diry]]
    :L [[posx posy] (rotate [dirx diry] (- 360 n))]
    :R [[posx posy] (rotate [dirx diry] (+ n))]
    :N [[posx (- posy n)] [dirx diry]]
    :E [[(+ posx n) posy] [dirx diry]]
    :S [[posx (+ posy n)] [dirx diry]]
    :W [[(- posx n) posy] [dirx diry]]))

(let [[[posx posy] _] (reduce step [[0 0] [1 0]] input/day-12-input)]
  (+ (Math/abs posx) (Math/abs posy)))
;; => 1010
