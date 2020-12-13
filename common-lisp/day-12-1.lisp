(load "day-12-input.lisp")

(defstruct state pos-x pos-y dir-x dir-y)

;; (defun single-step (state move)
;;   (with-accessors ((pos-x pos-x) (pos-y pos-y) (dir-x dir-x) (dir-y dir-y))
;;       state
;;    (destructuring-bind (action n)
;;        move
;;      (case action
;;        ('F (make-state :pos-x (+ pos-x dir-x) :pos-y (+ pos-y dir-y) :dir-x dir-x :dir-y dir-y))
;;        ('R nil)))))
