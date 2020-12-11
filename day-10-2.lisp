(load "day-10-input.lisp")

(defun next-adapters (n adapters)
  (loop for i from 1 to 3
     for m = (+ n i)
       when (member m adapters)
       collect m))

(defparameter *memo* (make-hash-table :test #'equal))

(defun solve (adapter data sum)
  (or (gethash (list adapter data sum) *memo*)
      (setf (gethash (list adapter data sum) *memo*)
            (let ((next-adapters (next-adapters adapter data)))
              (if next-adapters
                  (+ sum (loop for adapter in next-adapters
                            sum (solve adapter data sum)))
                  (1+ sum))))))

(princ (solve 0 *day-10-input* 0))

