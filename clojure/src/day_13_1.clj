(ns day-13-1
  (:require [day-13-input :as input]))

(defn departures [schedule minute]
  (->> schedule
       (mapcat (fn [s] (if (= 0 (mod minute s)) [s])))
       (into [])))

(defn schedule-seq [schedule start]
  (let [schedule (filter #(not (= :x %)) schedule)]
    (map (fn [minute]
           [minute (departures schedule minute)])
         (map #(+ start %) (range)))))

(defn first-departure [arrival schedule]
  (-> schedule
      (schedule-seq arrival)
      (->> (filter #(not (empty? (second %))))
           (take 1))
      first))

(let [[arrival schedule] input/day-13-input
      [departure [id & _]] (first-departure arrival schedule)]
  (* id (- departure arrival)))
;; => 3246
