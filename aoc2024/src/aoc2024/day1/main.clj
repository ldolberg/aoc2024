(ns aoc2024.day1.main)

(require '[clojure.string :as str])
(def input (map #(str/split % #"   ") (str/split (slurp "src/aoc2024/day1/input") #"\n")))



(defn solve-day1-a []
  (let [a (sort (map #(Integer/parseInt %) (map first input))) b (sort (map #(Integer/parseInt %) (map second input)))]
    (println (reduce + (map #(Math/abs (- %1 %2)) a b)))))

(defn solve-day1-b []
  (let [a (sort (map #(Integer/parseInt %) (map first input))) b (sort (map #(Integer/parseInt %) (map second input)))
    fb (frequencies b)]
    (print (reduce + (map #(apply * %) (map #(vector % (get fb %)) (filter #(contains? fb %) a)))))))

(defn solve-day []
  (solve-day1-a)
  (solve-day1-b))
