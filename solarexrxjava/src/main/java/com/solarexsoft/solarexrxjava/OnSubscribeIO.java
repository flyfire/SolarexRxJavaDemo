package com.solarexsoft.solarexrxjava;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:18/2020/3/7
 *    Desc:
 * </pre>
 */

public class OnSubscribeIO<T> implements ObservableOnSubscribe<T> {
    private static final String TAG = "OnSubscribeIO";
    private Observable<T> parent;
    private ExecutorService executorService;
    public OnSubscribeIO(Observable<T> parent) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, this + ",parent = " + parent);
            Thread.dumpStack();
        }
        this.parent = parent;
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void subscribe(final Observer<? super T> observer) {
        if (RxJavaPlugins.isDebug()) {
            Log.d(TAG, "parent = " + parent + ",this = " + this + " subscribe " + Thread.currentThread().getName());
            Thread.dumpStack();
        }
        executorService.execute(new Runnable() {
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
