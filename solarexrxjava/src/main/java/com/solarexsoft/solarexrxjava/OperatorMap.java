package com.solarexsoft.solarexrxjava;

import android.util.Log;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:57/2020/3/7
 *    Desc:
 * </pre>
 */

public class OperatorMap<T, R> implements Operator<R, T> {
    private static final String TAG = "OperatorMap";

    Function<? super T, ? extends R> function;

    public OperatorMap(Function<? super T, ? extends R> function) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + " --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        this.function = function;
    }

    @Override
    public Observer<? super T> apply(Observer<? super R> observer) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "observer = " + observer + ",this = " + this + " apply " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        return new MapSubscriber<>(observer, function);
    }

    private class MapSubscriber<T, R> extends Observer<T> {
        private static final String TAG = "MapSubscriber";
        private Observer<? super R> actual;
        private Function<? super T, ? extends R> transform;

        public MapSubscriber(Observer<? super R> actual, Function<? super T, ? extends R> transform) {
            if (RxJavaPlugins.isDebug()) {
                Log.d(TAG, "actual = " + actual + " --> " + Thread.currentThread().getName());
                Thread.dumpStack();
            }
            this.actual = actual;
            this.transform = transform;
        }

        @Override
        public void onNext(T t) {
            if (RxJavaPlugins.isDebug()) {
                Log.d(TAG, this + " onNext " + t + Thread.currentThread().getName());
                Thread.dumpStack();
            }
            if (RxJavaPlugins.isDebug()) {
                Log.d(TAG, this + " onNext " + t +  "before transform");
            }
            R r = transform.apply(t);
            if (RxJavaPlugins.isDebug()) {
                Log.d(TAG, this + " onNext " + r + " after transform");
            }
            actual.onNext(r);
        }

        @Override
        public void onError(Throwable throwable) {
            actual.onError(throwable);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
