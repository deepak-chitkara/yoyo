(ns ^{:clojure.tools.namespace.repl/load false
      :clojure.tools.namespace.repl/unload false}
  yoyo.protocols
  (:require [cats.core :as c]
            [cats.protocols :as cp]))

(declare component-monad)

(defprotocol IComponent
  (stop! [_]))

(defrecord YoyoComponent [v stop-fn]
  IComponent
  (stop! [_]
    (when stop-fn
      (stop-fn))
    v)

  cp/Context
  (get-context [_]
    component-monad)

  #?@(:clj
       [clojure.lang.IDeref,
        (deref [_]
               v)]

       :cljs
       [IDeref
        (-deref [_]
                v)]))

(def component-monad
  (reify
    cp/Functor
    (fmap [m f {outer-v :v, outer-stop-fn :stop-fn}]
      (->YoyoComponent (f outer-v) outer-stop-fn))

    cp/Monad
    (mreturn [_ v]
      (->YoyoComponent v nil))

    (mbind [_ {outer-v :v, outer-stop-fn :stop-fn} f]
      (let [{inner-v :v, inner-stop-fn :stop-fn} (try
                                                   (f outer-v)
                                                   (catch #?(:clj Throwable, :cljs js/Error) t
                                                     (try
                                                       (when outer-stop-fn
                                                         (outer-stop-fn))

                                                       (finally
                                                         (throw t)))))]
        (->YoyoComponent inner-v
                         (cond
                           (and inner-stop-fn outer-stop-fn) (fn []
                                                               (try
                                                                 (when inner-stop-fn
                                                                   (inner-stop-fn))

                                                                 (finally
                                                                   (when outer-stop-fn
                                                                     (outer-stop-fn)))))
                           outer-stop-fn outer-stop-fn
                           inner-stop-fn inner-stop-fn))))))
