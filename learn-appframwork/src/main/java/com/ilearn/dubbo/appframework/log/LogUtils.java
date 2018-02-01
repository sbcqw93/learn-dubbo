package com.ilearn.dubbo.appframework.log;

/**
 * Created by Administrator on 2018/2/1 0001.
 */
public class LogUtils {

    public static String stackTrace(Throwable e) {
        if (null == e) {
            return "Throwable is empty.";
        } else {
            String L = "\n";
            StringBuffer buffer = new StringBuffer("\n");
            buffer.append(e).append("\n");
            StackTraceElement[] trace = e.getStackTrace();

            for (int i = 0; i < trace.length; ++i) {
                buffer.append("\tat " + trace[i]).append("\n");
            }

            return buffer.toString();
        }
    }
}
