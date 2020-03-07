package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:46/2020/3/7
 *    Desc:
 * </pre>
 */

public class Observable<T> {
    private ObservableOnSubscribe onSubscribe;

    public Observable(ObservableOnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
        return new Observable<>(onSubscribe);
    }

    public void subscribe(Observer<? super T> subscriber){
        onSubscribe.subscribe(subscriber);
    }
}
