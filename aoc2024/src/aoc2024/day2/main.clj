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


(defn all-copies-with-one-removed [xs] 
  (loop [index 0
         result []]
    (if (= index (count xs))
      result
      (recur (inc index) 
             (conj result (concat (take index xs) (drop (inc index) xs)))))))


(defn safe-list [xs]
  (if (or (is-all-increasing? xs) (is-all-decreasing? xs)) 1 0)) 

(defn solve-day2-a []
  (reduce + (map safe-list input))) 

(def is-super-safe (fn [x] (reduce + (map safe-list  (all-copies-with-one-removed x)))))

(defn solve-day2-b [] 
  (let [unsafe (filter #(= 0 (safe-list %)) input)]
    (+ (reduce + (map safe-list input)) (count (filter #(> % 0) (map is-super-safe unsafe))))))

(defn solve-day []
  (println (solve-day2-a))
  (println (solve-day2-b)))
