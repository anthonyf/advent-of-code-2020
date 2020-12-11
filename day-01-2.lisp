(load "day-01-input.lisp")


(princ
 (destructuring-bind ((n1 n2 n3))
     (loop for (n1 . rest) on *day-01-input*
        append (loop for (n2 . rest) on rest
                  append (loop for n3 in rest
                            if (= 2020 (+ n1 n2 n3))
                            collect (list n1 n2 n3))))
   (* n1 n2 n3)))
