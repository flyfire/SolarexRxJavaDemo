package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:37/2020/3/7
 *    Desc:
 * </pre>
 */

public interface Function<T, R> {
    R apply(T t);
}
