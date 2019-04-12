package com.yuri.xlog.sample;

import com.yuri.xlog.XLog;

public class MyLog extends XLog {


    public static void logx(String message) {
        getElemeemememe();
        d(message + ">>>>>>>>>>>>>>>>>>>");
    }

    @Override
    protected int getMMM() {
        return 1111111111;
    }

    public static StackTraceElement getElemeemememe() {
        System.out.println("geElement2222222222xxxxxxxxxx22");
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            System.out.println(className);
            boolean isLogMethod = stackTraceElement.getClassName().equals(MyLog.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    @Override
    protected StackTraceElement getElement() {
        System.out.println("geElement222222222222");
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            System.out.println(className);
            boolean isLogMethod = stackTraceElement.getClassName().equals(MyLog.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
