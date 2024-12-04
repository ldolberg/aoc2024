(ns aoc2024.day3.main)

(require '[clojure.string :as str])

(def input (slurp "src/aoc2024/day3/input"))

(defn parse-mul-instruction [s]
  (when-let [[_ x y] (re-matches #"mul\((\d{1,3}),(\d{1,3})\)" s)]
    (* (Integer/parseInt x) (Integer/parseInt y))))

(defn process-instructions [input]
  (loop [chars (seq input)
         enabled? true
         current-num ""
         results []]
    (if (empty? chars)
      (reduce + results)
      (let [c (first chars)
            rest-chars (rest chars)]
        (cond
          ; Check for do() instruction
          (and (= c \d) (>= (count rest-chars) 3) (= (apply str (take 3 rest-chars)) "o()"))
          (recur (drop 3 rest-chars) true current-num results)
          
          ; Check for don't() instruction
          (and (= c \d) (>= (count rest-chars) 5) (= (apply str (take 5 rest-chars)) "on't()"))
          (recur (drop 5 rest-chars) false current-num results)
          
          ; Process mul instruction if enabled
          :else 
          (let [mul-str (re-find #"mul\(\d{1,3},\d{1,3}\)" (apply str c rest-chars))]
            (if mul-str
              (recur (drop (count mul-str) chars) 
                     enabled?
                     ""
                     (if enabled?
                       (if-let [result (parse-mul-instruction mul-str)]
                         (conj results result)
                         results)
                       results))
              (recur rest-chars enabled? current-num results))))))))

(defn solve-part1 [input]
  (process-instructions input))

(defn solve-day []
  (println "Part 1:" (solve-part1 input))) 