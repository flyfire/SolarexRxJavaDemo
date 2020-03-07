package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:53/2020/3/7
 *    Desc:
 * </pre>
 */

public class OnSubscribeLift<T, R> implements ObservableOnSubscribe<R> {

    ObservableOnSubscribe<T> parent;
    Operator<? extends T, ? super R> operator;
    
    @Override
    public void subscribe(Observer<? extends R> observer) {

    }
}
