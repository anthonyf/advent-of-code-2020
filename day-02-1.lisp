(load "day-02-input.lisp")

(defun check-password (min max c pass)
  (let ((n (count c pass)))
    (and (>= n min)
         (<= n max))))

(princ
 (loop for (min max c pass) in *day-02-input*
    if (check-password min max c pass)
    sum 1))
