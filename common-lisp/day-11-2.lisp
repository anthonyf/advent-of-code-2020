(load "day-11-input.lisp")
(ql:quickload "alexandria")

(defun adjacent-seats (seats row col)
  (let ((rows (array-dimension seats 0))
        (cols (array-dimension seats 1))
        (offsets '((0 . 1) (0 . -1) (1 . 0) (-1 . 0) (1 . -1) (1 . 1) (-1 . 1) (-1 . -1))))
    (loop for (orow . ocol) in offsets
          for nrow = (+ row orow)
          for ncol = (+ col ocol)
          when (and (>= nrow 0) (< nrow rows)
                    (>= ncol 0) (< ncol cols))
          collect (aref seats nrow ncol))))

(defun ocupied-adjacent-p (seats row col)
  (member #\# (adjacent-seats seats row col)))

(defun single-step (seats)
  (let ((new-seats (alexandria:copy-array seats))
        (rows (array-dimension seats 0))
        (cols (array-dimension seats 1)))
    (loop for row from 0 below rows
          do (loop for col from 0 below cols
                   do (let ((adjacent-seats (adjacent-seats seats row col)))
                        (cond
                          ((and (eq #\L (aref seats row col))
                                (not (member #\# adjacent-seats)))
                           (setf (aref new-seats row col) #\#))
                          ((and (eq #\# (aref seats row col))
                                (>= (count #\# adjacent-seats) 4))
                           (setf (aref new-seats row col) #\L))))))
    
    new-seats))

(princ
 (let ((starting-seats *day-11-input*))
   (loop for prev-seats = starting-seats then seats
         and seats = (single-step starting-seats) then (single-step seats)
         while (not (equalp prev-seats seats))
         finally (return (let ((rows (array-dimension seats 0))
                               (cols (array-dimension seats 1)))
                           (loop for row from 0 below rows
                                 sum (loop for col from 0 below cols
                                           if (eq #\# (aref seats row col))
                                             count 1)))))))
