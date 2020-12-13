(load "day-02-input.lisp")

(defun check-password (p1 p2 c pass)
  (let ((a (eq c (aref pass (1- p1))))
        (b (eq c (aref pass (1- p2)))))
    (or (and (not a) b)
        (and a (not b)))))

(princ
 (loop for (p1 p2 c pass) in *day-02-input*
    if (check-password p1 p2 c pass)
    sum 1))
