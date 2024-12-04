(ns aoc2024.day3.main)

(require '[clojure.string :as str])

(def input (slurp "src/aoc2024/day3/input"))

(defn parse-mul-instruction [s]
  (when-let [[_ x y] (re-matches #"mul\((\d{1,3}),(\d{1,3})\)" s)]
    (* (Integer/parseInt x) (Integer/parseInt y))))

(defn solve-part1 [input]
  (->> input
       (re-seq #"mul\(\d{1,3},\d{1,3}\)")  ; Find all potential mul instructions
       (keep parse-mul-instruction)          ; Parse valid ones and remove nils
       (reduce +)))
                                ; Sum all results
(defn solve-part2 [input]
  (let [sections (str/split input #"(do\(\)|don't\(\))")  ; Split on exact markers
        markers (or (re-seq #"(do\(\)|don't\(\))" input) [])  ; Get all markers
        initial-section (first sections)
        rest-sections (map vector (rest sections) markers)]
    (loop [sum (solve-part1 initial-section)
           enabled? true
           remaining rest-sections]
      (if (empty? remaining)
        sum
        (let [[section [marker]] (first remaining)
              new-enabled? (= marker "do()")
              new-sum (if new-enabled?
                       (+ sum (solve-part1 section))
                       sum)]
          (recur new-sum new-enabled? (rest remaining)))))))

(defn solve-day []
  (println "Part 1:" (solve-part1 input))
  (println "Part 2:" (solve-part2 input)))

