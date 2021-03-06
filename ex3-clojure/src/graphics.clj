(ns graphics
  (require [parse])
  (:require [quil.core :as q]))

(defn draw-tile[b x y]
  (q/stroke 0 0 0)
  (case b
    \r (q/fill 255 0 0)
    \g (q/fill 0 255 0)
    \b (q/fill 0 0 255)
    0  (q/fill 255 255 255)
    1  (q/fill 191 127 63)
    5  (q/fill 127 255 127)
    10 (q/fill 0 127 0)
    50 (q/fill 165 165 165)
    100 (q/fill 76 76 255)
    (q/fill 0 0 0))

  (q/rect x y 20 20))

(defn draw-dot[x y]
  (q/stroke 0 0 0)
  (q/fill 0 0 0)
  (q/ellipse x y 5 5))

(defn draw-dia[x y]
  (q/stroke 255 105 180)
  (q/fill 255 105 180)
  (q/quad x (- y 3) (+ x 3) y x (+ y 3) (- x 3) y))

(defn draw-tri[x y]
  (q/stroke 0 255 255)
  (q/fill 0 255 255)
  (q/triangle x (- y 2) (- x 2) (+ y 2) (+ x 2) (+ y 2)))

(defn enumerate[idx itm]
  [idx itm])

(defn draw [grid path open closed]
  (q/smooth)
  (q/background 0)

  (let [s (.start grid)
        e (.end grid)]
    (doseq [row (map-indexed enumerate (.weights grid))]
      (doseq [tile (map-indexed enumerate (second row))]
        (let [y (first row)
              ya (+ 10 (* 20 y))
              x (first tile)
              xa (+ 10 (* 20 x))
              b (second tile)
              c (if (= [x y] s)
                  \r
                  (if (= [x y] e)
                    \g
                    b))]
          (draw-tile c (* 20 x) (* 20 y))
          (if (some #{[x y]} path)
            (draw-dot xa ya)
            (if (contains? open [x y])
              (draw-tri xa ya)
              (if (contains? closed [x y])
                (draw-dia xa ya)))))))))

(defn draw-grid [grid path open closed title]
  (let [d (parse/dimensions grid)
        x (first d)
        y (second d)
        h (* 20 x)
        w (* 20 y)]
    (q/sketch
      :title title
      :setup (partial draw grid path open closed)
      :size [h w])))
