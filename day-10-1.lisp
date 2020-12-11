(load "day-10-input.lisp")

(defun next-adapters (n adapters)
  (loop for i from 1 to 3
     for m = (+ n i)
       when (member m adapters)
       collect m))

(princ
 (let ((diffs (loop for prev-adapter = 0 then adapter
                 and adapter = (first (next-adapters 0 *day-10-input*)) then (first (next-adapters adapter *day-10-input*))
                 while adapter
                 collect (- adapter prev-adapter))))
   (* (count 1 diffs)
      (1+ (count 3 diffs)))))

