(ns day-11-2
  (:require [day-11-input :as input]))

(def dirs [[1 0] [0 1] [-1 0] [0 -1] [-1 1] [1 -1] [-1 -1] [1 1]])

(defn see-anybody? [[row col] diri input]
  (let [[drow dcol] (dirs diri)
                rows (count input)
                cols (count (first input))]
    (loop [[row col] [row col]]
      (if (and (>= row 0) (< row rows)
               (>= col 0) (< col cols))
        (if (= :# (input row col))
          true
          (recur [(+ drow row) (+ dcol col)]))        
        false))))

(defn num-seen [[row col] input]
  (count (mapcat (fn [diri]
                   (if (see-anybody? [row col] diri input)
                     [:#]
                     nil))
                 (range (count dirs)))))


(defn step [input]
  (let [indexes (for [row (range (count input))
                      col (range (count (first input)))]
                  [row col])]
    (reduce (fn [output [row col]]
              (let [seat (get-in input [row col])]
               (cond (and (= :L seat)
                          (not (see-anybody? [row col] input)))
                     (assoc-in input [row col] :#)
                    
                     (and (= :# seat)
                          (> (num-seen [row col] input) 4))
                     (assoc-in input [row col] :L)

                     :else input)))
            indexes)))
