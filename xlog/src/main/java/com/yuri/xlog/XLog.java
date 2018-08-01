package com.yuri.xlog;


import com.yuri.xlog.util.ObjParser;
import com.yuri.xlog.util.Util;
import com.yuri.xlog.util.XmlJsonParser;

/**
 * Log相关封装 <br>
 * 能够自动打印出方法名以及类名
 */
public class XLog {
    private static final int LOG_V = 0;
    private static final int LOG_I = 1;
    private static final int LOG_D = 2;
    private static final int LOG_W = 3;
    private static final int LOG_E = 4;
    private static final int LOG_N = 5;

    private static Settings mSettings;
    private static LogFile mLogFile;
    private static LogcatSaver mLogcatSaver;
    private static boolean mIsWriteToFile = false;

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private static final int CHUNK_SIZE = 4000;

    public static Settings initialize() {
        mSettings = Settings.getInstance();
        return mSettings;
    }

    public static Settings getSettings() {
        if (mSettings == null) {
            initialize();
        }
        return mSettings;
    }

    private static boolean isDebug() {
        return getSettings().isDebug;
    }

    private static String getTag() {
        return getSettings().appTag + "/";
    }

    public static void v() {
        print(LOG_V, "");
    }

    /**
     * format log: Log.v("title:%s,name:%s", title, name);
     * @param message message
     * @param args args
     */
    public static void v(String message, Object... args) {
        print(LOG_V, message, args);
    }

    /**
     * 带log前缀的方法，不会经过解析当前方法名以及类名的步骤，直接调用系统的Log.v打印log
     */
    public static void logv(String message, Object... args) {
        logv("", message, args);
    }

    public static void logv(String tag, String message, Object... args) {
        if (isDebug()) {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            byte[] bytes = message.getBytes();
            int length = bytes.length;

            if (length <= CHUNK_SIZE) {
                android.util.Log.v(getTag() + tag, message);
                return;
            }

            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                android.util.Log.d(getTag() + tag, new String(bytes, i, count));
            }
            android.util.Log.v(getTag() + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("V", getTag() + tag, message));
        }
    }

    public static void i() {
        print(LOG_I, "");
    }

    public static void i(String message, Object... args) {
        print(LOG_I, message, args);
    }

    public static void logi(String message, Object... args) {
        logi("", message, args);
    }

    public static void logi(String tag, String message, Object... args) {
        if (isDebug()) {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            byte[] bytes = message.getBytes();
            int length = bytes.length;

            if (length <= CHUNK_SIZE) {
                android.util.Log.i(getTag() + tag, message);
                return;
            }

            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                android.util.Log.i(getTag() + tag, new String(bytes, i, count));
            }
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("I", getTag() + tag, message));
        }
    }

    public static void d() {
        print(LOG_D, "");
    }

    public static void d(String message, Object... args) {
        print(LOG_D, message, args);
    }

    public static void logd(String message, Object... args) {
        logd("", message, args);
    }

    public static void logd(String tag, String message, Object... args) {
        if (isDebug()) {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            byte[] bytes = message.getBytes();
            int length = bytes.length;

            if (length <= CHUNK_SIZE) {
                android.util.Log.d(getTag() + tag, message);
                return;
            }

            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                android.util.Log.d(getTag() + tag, new String(bytes, i, count));
            }
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("D", getTag() + tag, message));
        }
    }

    public static void w() {
        print(LOG_W, "");
    }

    public static void w(String message, Object... args) {
        print(LOG_W, message, args);
    }

    public static void logw(String message, Object... args) {
        logw("", message, args);
    }

    public static void logw(String tag, String message, Object... args) {
        if (isDebug()) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.w(getTag() + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("W", getTag() + tag, message));
        }
    }

    public static void e() {
        print(LOG_E, "");
    }

    public static void e(String message, Object... args) {
        print(LOG_E, message, args);
    }

    public static void e(Exception e) {
        printStackTrace(e);
    }

    public static void e(Throwable throwable) {
        printStackTrace(throwable);
    }

    public static void loge(String message, Object... args) {
        loge("", message, args);
    }

    public static void loge(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        android.util.Log.e(getTag() + tag, message);

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("E", getTag() + tag, message));
        }
    }

    public static void net() {
        print(LOG_N, "");
    }

    public static void net(String message, Object... args) {
        print(LOG_N, message, args);
    }

    public static void lognet(String tag, String message, Object... args) {
        if (isDebug()) {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            byte[] bytes = message.getBytes();
            int length = bytes.length;

            if (length <= CHUNK_SIZE) {
                android.util.Log.d(getTag() + tag, message);
                return;
            }

            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                android.util.Log.d(getSettings().netTag + "/" + tag, new String(bytes, i, count));
            }
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("N", getSettings().netTag + "/" + tag, message));
        }
    }

    private static void print(int priority, String message, Object... args) {
        if (!isDebug() && priority != LOG_E) {
            return;
        }

        if (args.length > 0) {
            message = String.format(message, args);
        }

        String result[] = formatMessage(message);
        switch (priority) {
            case LOG_V:
                logv(result[0], result[1]);
                break;
            case LOG_I:
                logi(result[0], result[1]);
                break;
            case LOG_D:
                logd(result[0], result[1]);
                break;
            case LOG_W:
                logw(result[0], result[1]);
                break;
            case LOG_E:
                loge(result[0], result[1]);
                break;
            case LOG_N:
                lognet(result[0], result[1]);
                break;
        }
    }

    /**
     * 格式化打印Json数据，方便你阅读Logcat JSON数据
     */
    public static void json(String json) {
        if (isDebug()) {
            print(LOG_D, XmlJsonParser.json(json));
        }
    }

    /**
     * 格式化打印xml数据
     */
    public static void xml(String xml) {
        if (isDebug()) {
            print(LOG_D, XmlJsonParser.xml(xml));
        }
    }

    /**
     * 打印对象内部数据，包括但不限于List，String[]等等
     * @deprecated
     */
    public static void object(Object object) {
        if (isDebug()) {
            print(LOG_D, ObjParser.parseObj(object));
        }
    }

    /**
     * 打印对象内部数据，包括但不限于List，String[]等等
     */
    public static void xobject(Object object) {
        if (isDebug()) {
            print(LOG_D, ObjParser.parseObj(object));
        }
    }

    private static String[] formatMessage(String mesage) {
        StackTraceElement stackTraceElement = getTargetStackTraceElement();
        String[] formatMsg = new String[2];
        String className = stackTraceElement.getClassName();
        formatMsg[0] = className.substring(className.lastIndexOf(".") + 1);
        if (mesage.equals("")) {
            formatMsg[1] = stackTraceElement.getMethodName() + "(";
        } else {
            formatMsg[1] = mesage + " ==> " + stackTraceElement.getMethodName() + "(";
        }

        if (getSettings().showMethodLink) {
            formatMsg[1] += stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber();
        }
        formatMsg[1] += ")";
        if (getSettings().showThreadInfo) {
            formatMsg[1] += getThreadName();
        }
        return formatMsg;
    }

    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(XLog.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    private static String getThreadName() {
        return " Thread:" + Thread.currentThread().getName();
    }

    public static void printStackTrace() {
        StackTraceElement[] traceElements = Thread.currentThread()
                .getStackTrace();
        if (traceElements != null) {
            for (StackTraceElement element : traceElements) {
                XLog.logd(getSettings().appTag, element.toString());
            }
        }
    }

    public static void printStackTrace(Throwable e) {
        StackTraceElement[] traceElements = e.getStackTrace();
        if (traceElements != null) {
            for (StackTraceElement element : traceElements) {
                XLog.loge(getSettings().appTag, element.toString());
            }
        }
    }

    /**
     * Start saving log to file.
     */
    public static boolean startSaveToFile() {
        android.util.Log.d(getSettings().appTag, "startSaveToFile()");
        stopAndSave();

        mLogFile = new LogFile(Util.getTime() + ".txt");

        mIsWriteToFile = true;

        boolean isOpen = mLogFile.open();
        if (isOpen) {
            mLogFile.writeLog("**********Start Writing Log at time "
                    + Util.getTime() + "**********\n");
        } else {
            android.util.Log.e(getSettings().appTag, "startSaveToFile.can not open file");
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
        android.util.Log.d(getSettings().appTag, "stopAndSave()");
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
