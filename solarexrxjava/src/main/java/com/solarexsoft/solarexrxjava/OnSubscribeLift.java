package com.solarexsoft.solarexrxjava;

import android.util.Log;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:53/2020/3/7
 *    Desc:
 * </pre>
 */

public class OnSubscribeLift<T, R> implements ObservableOnSubscribe<R> {
    private static final String TAG = "OnSubscribeLift";
    ObservableOnSubscribe<T> parent;
    Operator<? extends R, ? super T> operator;

    public OnSubscribeLift(ObservableOnSubscribe<T> onSubscribe, Function<? super T, ? extends R> function) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "this = " + this + ",parent = " + onSubscribe);
            if (RxJavaPlugins.isShowStackTrace()) {
                Thread.dumpStack();
            }
        }
        this.parent = onSubscribe;
        this.operator = new OperatorMap<>(function);
    }
    @Override
    public void subscribe(Observer<? super R> observer) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "this = " + this + " subscribe " + Thread.currentThread().getName() + ",observer = " + observer);
            if (RxJavaPlugins.isShowStackTrace()) {
                Thread.dumpStack();
            }
        }
        Observer<? super T> st = operator.apply(observer);
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "this = " + this + " subscribe " + Thread.currentThread().getName() + ", st = " + st);
            if (RxJavaPlugins.isShowStackTrace()) {
                Thread.dumpStack();
            }
        }
        parent.subscribe(st);
    }
}
