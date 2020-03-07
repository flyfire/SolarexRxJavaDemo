package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:44/2020/3/7
 *    Desc:
 * </pre>
 */

public interface Operator<T, R> extends Function<Observer<? super T>, Observer<? super R>> {
}
