(load "day-01-input.lisp")

(princ
 (destructuring-bind ((n1 . n2))
     (loop for (n1 . rest) on *day-01-input*
        append (loop for n2 in rest
                  if (= 2020 (+ n1 n2))
                  collect (cons n1 n2)))
   (* n1 n2)))
