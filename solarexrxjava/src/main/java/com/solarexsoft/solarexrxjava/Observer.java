package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:41/2020/3/7
 *    Desc:
 * </pre>
 */

public abstract class Observer<T> {
    public abstract void onNext(T t);

    public abstract void onError(Throwable throwable);

    public abstract void onComplete();
}
