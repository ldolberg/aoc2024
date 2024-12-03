(ns aoc2024.day2.main)

(require '[clojure.string :as str])
(def input (map #(map (fn [x] (Integer/parseInt x)) %) (map #(str/split % #" ") (str/split (slurp "src/aoc2024/day2/input") #"\n"))))

(defn is-all-increasing? [xs]
  (loop [xs xs
         result true]
    (if (or (< (count xs) 2) (not result))
      result
      (recur (rest xs) (and result (>= (- (second xs) (first xs)) 1) (<= (- (second xs) (first xs)) 3))))))


(defn is-all-decreasing? [xs]
  (loop [xs xs
         result true]
    (if (or (= (count xs) 1) (not result))
      result
      (recur (rest xs) (and result (>= (- (first xs) (second xs)) 1) (<= (- (first xs) (second xs)) 3))))))




(defn solve-day2-a []
  (count (filter #(or (is-all-increasing? %) (is-all-decreasing? %)) input)))


(println (is-all-increasing? (list 4 2 3 4 1)))

(defn solve-day2-b []
  (println "solve day 2 b"))

(defn solve-day []
  (println (solve-day2-a))
  (solve-day2-b))
