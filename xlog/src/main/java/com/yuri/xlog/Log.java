package com.yuri.xlog;


/**
 * Log相关封装 <br>
 * 能够自动打印出方法名以及类名
 */
public class Log {
    public static final int INDEX_BASE = 5;

    public static Settings mSettings;
    private static LogFile mLogFile;
    private static LogcatSaver mLogcatSaver;
    private static boolean mIsWriteToFile = false;

    public static void initialize(Settings settings) {
        mSettings = settings;
    }

    public static void v() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            logv(result[0], result[1]);
        }
    }

    /**
     * 支持格式化打印 比如 ： Log.v("title:%s,name:%s", title, name);
     * @param message message
     * @param args args
     */
    public static void v(String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            String result[] = formatMessage(message);
            logv(result[0], result[1]);
        }
    }

    /**
     * 调用这个方法直接打印，不打印当前类名、方法名等
     */
    public static void logv(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.v(mSettings.appTag + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("V", mSettings.appTag + tag, message));
        }
    }

    public static void i() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            logi(result[0], result[1]);
        }
    }

    public static void i(String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            String result[] = formatMessage(message);
            logi(result[0], result[1]);
        }
    }

    /**
     * 直接打印，不打印当前类名、方法名等
     */
    public static void logi(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.i(mSettings.appTag + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("I", mSettings.appTag + tag, message));
        }
    }

    public static void d() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            logd(result[0], result[1]);
        }
    }

    public static void d(String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            String result[] = formatMessage(message);
            logd(result[0], result[1], args);
        }
    }

    /**
     * 直接打印，不打印当前类名、方法名等
     */
    public static void logd(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.d(mSettings.appTag + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("D", mSettings.appTag + tag, message));
        }
    }

    public static void w() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            logw(result[0], result[1]);
        }
    }

    public static void w(String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            String result[] = formatMessage(message);
            logw(result[0], result[1]);
        }
    }

    public static void logw(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.w(mSettings.appTag + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("W", mSettings.appTag + tag, message));
        }
    }

    public static void e() {
        String result[] = formatMessage("");
        loge(result[0], result[1]);
    }

    public static void e(String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        String result[] = formatMessage(message);
        loge(result[0], result[1]);
    }

    public static void e(Exception e) {
        printStackTrace(e);
    }

    public static void e(Throwable throwable) {
        printStackTrace(throwable);
    }

    public static void loge(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        android.util.Log.e(mSettings.appTag + tag, message);

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("E", mSettings.appTag + tag, message));
        }
    }

    public static void net() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            lognet(result[0], result[1]);
        }
    }

    public static void net(String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            String result[] = formatMessage(message);
            lognet(result[0], result[1]);
        }
    }

    /**
     * 网络接口log
     */
    public static void lognet(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.d(mSettings.netTag + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("N", mSettings.appTag + tag, message));
        }
    }

    private static String[] formatMessage(String mesage) {
        String[] caller = getCaller();
        String[] formatMsg = new String[2];
        formatMsg[0] = caller[0];
        formatMsg[1] = mesage + " ==> " + caller[1] + "(" + caller[2] + ":" + caller[3] + ")"
                + getThreadName();
        return formatMsg;
    }

    /**
     * 获取调用的类名和方法名。
     *
     * @return String[0]为类名，String[1]为方法名。
     */
    private static String[] getCaller() {
        String caller[] = new String[4];
        try {
            StackTraceElement[] traceElements = Thread.currentThread()
                    .getStackTrace();
            String className = traceElements[INDEX_BASE].getClassName();
            className = className.substring(className.lastIndexOf(".") + 1);

            caller[0] = className;
            caller[1] = traceElements[INDEX_BASE].getMethodName();
            caller[2] = traceElements[INDEX_BASE].getFileName();
            caller[3] = traceElements[INDEX_BASE].getLineNumber() + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caller;
    }

    private static String getThreadName() {
        return " Thread:" + Thread.currentThread().getName();
    }

    public static void printStackTrace() {
        StackTraceElement[] traceElements = Thread.currentThread()
                .getStackTrace();
        if (traceElements != null) {
            for (int i = 0; i < traceElements.length; i++) {
                Log.logd("", traceElements[i].toString());
            }
        }
    }

    public static void printStackTrace(Throwable e) {
        StackTraceElement[] traceElements = e.getStackTrace();
        if (traceElements != null) {
            for (int i = 0; i < traceElements.length; i++) {
                Log.loge("", traceElements[i].toString());
            }
        }
    }

    /**
     * Start saving log to file.
     */
    public static boolean startSaveToFile() {
        android.util.Log.d(mSettings.appTag, "startSaveToFile()");
        stopAndSave();

        mLogFile = new LogFile(Util.getTime() + ".txt");

        mIsWriteToFile = true;

        boolean isOpen = mLogFile.open();
        if (isOpen) {
            mLogFile.writeLog("**********Start Writing Log at time "
                    + Util.getTime() + "**********\n");
        } else {
            android.util.Log.e(mSettings.appTag, "startSaveToFile.can not open file");
            mLogFile = null;
            return false;
        }

        mLogcatSaver = new LogcatSaver(Util.getTime() + "_logcat.txt");
        mLogcatSaver.start();
        return true;
    }

    /**
     * Stop log saving.
     */
    public static void stopAndSave() {
        android.util.Log.d(mSettings.appTag, "stopAndSave()");
        if (mLogFile != null) {
            mLogFile.close();
            mLogFile = null;
        }
        if (mLogcatSaver != null) {
            mLogcatSaver.stop();
            mLogcatSaver = null;
        }

        mIsWriteToFile = false;
    }

    private static String getLogString(String level, String tag, String message) {
        return level + " " + Util.getTime() + "  " + tag + "  "
                + message + "\n";
    }
}
