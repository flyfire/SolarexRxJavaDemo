package com.solarexsoft.solarexrxjava;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 13:21/2020/3/7
 *    Desc:
 * </pre>
 */

public class RxJavaPlugins {
    private static boolean isDebug;

    public static void openDebug() {
        isDebug = true;
    }

    public static void closeDebug() {
        isDebug = false;
    }

    public static boolean isDebug() {
        return isDebug;
    }
}
