(ns day-13-2
  (:require [day-13-input :as input]))


(defn interval [n] (->> (iterate #(+ n %) 0)
                        (map (fn [x] [x #{n}]))))

(defn merge-schedules [[[a-timestamp a-ids] & aN :as a]
                       [[b-timestamp b-ids] & bN :as b]]
  (cond
    (= a-timestamp b-timestamp)
    (lazy-seq (cons [a-timestamp (into #{} (concat a-ids b-ids))] (merge-schedules aN bN)))

    (< a-timestamp b-timestamp)
    (lazy-seq (cons [a-timestamp a-ids] (merge-schedules aN b)))

    :else
    (lazy-seq (cons [b-timestamp b-ids] (merge-schedules a bN)))))

(take 100 (merge-schedules (interval 5) (interval 10)))

(defn schedule-seq
  ([schedule skip limit]
   (->> (schedule-seq schedule)
        (drop-while (fn [[ts _]] (< ts skip)))
        (take limit)))
  ([schedule]
   (->> schedule
        (filter #(not (= :x %)))
        (map #(interval %))
        (reduce merge-schedules))))

(->> (schedule-seq (second input/test-input))
     (drop-while (fn [[ts _]] (< ts 1068781)))
     (take 10))

(defn solve [schedule]
  (loop [[[timestamp ids] & rest-schedule-seq :as schedule-seq] (schedule-seq schedule)
         icol 0
         best nil]
    (if (= icol (count schedule))
      best
      (let [id (nth schedule icol)]
        (case id
          :x (recur schedule-seq (+ 1 icol) (or best timestamp))
          (if (contains? ids id)
            (recur rest-schedule-seq (+ 1 icol) (or best timestamp))
            (if (= icol 0)
              (recur rest-schedule-seq 0 nil)
              (recur schedule-seq 0 nil))))))))

(let [[_ schedule] input/day-13-input
      ]
  (solve schedule))
;; =>   1982840729 wrong!
;; 100000000000000

;; => 1068781
