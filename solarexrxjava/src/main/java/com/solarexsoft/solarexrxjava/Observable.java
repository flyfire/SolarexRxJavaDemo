package com.solarexsoft.solarexrxjava;

import android.util.Log;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:46/2020/3/7
 *    Desc:
 * </pre>
 */

public class Observable<T> {
    private static final String TAG = "Observable";
    private ObservableOnSubscribe onSubscribe;

    public Observable(ObservableOnSubscribe<T> onSubscribe) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + ",onSubscribe = " + onSubscribe + " --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
        return new Observable<>(onSubscribe);
    }

    public void subscribe(Observer<? super T> subscriber){
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + " subscribe observer = " + subscriber + " --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        onSubscribe.subscribe(subscriber);
    }

    public <R> Observable<R> map(Function<? super T, ? extends R> function) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + " map --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        return new Observable<R>(new OnSubscribeLift<T,R>(onSubscribe, function));
    }

    public Observable<T> subscribeOnIO() {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + " subscribeOnIO --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        return create(new OnSubscribeIO<T>(this));
    }

    public Observable<T> subscribeOnMain() {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + " subscribeOnMain --> " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        return create(new OnSubscribeMain<T>(this));
    }
}
