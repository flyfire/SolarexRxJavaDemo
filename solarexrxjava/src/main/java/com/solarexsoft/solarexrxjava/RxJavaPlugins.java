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
    private static boolean showStackTrace;

    public static void openDebug() {
        isDebug = true;
    }

    public static void closeDebug() {
        isDebug = false;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static boolean isShowStackTrace() {
        return showStackTrace;
    }

    public static void openStackTrace() {
        showStackTrace = true;
    }

    public static void closeStackTrace() {
        showStackTrace = false;
    }
}
