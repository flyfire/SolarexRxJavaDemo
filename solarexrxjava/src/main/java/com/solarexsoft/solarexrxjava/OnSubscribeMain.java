package com.solarexsoft.solarexrxjava;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:12/2020/3/7
 *    Desc:
 * </pre>
 */

public class OnSubscribeMain<T> implements ObservableOnSubscribe<T> {
    private static final String TAG = "OnSubscribeMain";
    private Handler handler;
    private Observable<T> parent;

    public OnSubscribeMain(Observable<T> parent) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + ",parent = " + parent);
            Thread.dumpStack();
        }
        this.parent = parent;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void subscribe(final Observer<? super T> observer) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "parent = " + parent + ",this = " + this + " subscribe " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (RxJavaPlugins.isDebug()) {
                    Log.d(TAG, this + " run " + Thread.currentThread().getName());
                    Thread.dumpStack();
                }
                parent.subscribe(observer);
            }
        });
    }
}
