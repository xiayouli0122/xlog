package com.yuri.xlog;


import com.yuri.xlog.util.ObjParser;
import com.yuri.xlog.util.Util;
import com.yuri.xlog.util.XmlJsonParser;

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

    private static String TAG;

    public static void initialize(Settings settings) {
        mSettings = settings;
        TAG = mSettings.appTag + "/";
    }

    public static void v() {
        if (mSettings.isDebug) {
            String result[] = formatMessage("");
            logv(result[0], result[1]);
        }
    }

    /**
     * format log: Log.v("title:%s,name:%s", title, name);
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
     * 带log前缀的方法，不会经过解析当前方法名以及类名的步骤，直接调用系统的Log.v打印log
     */
    public static void logv(String message, Object... args) {
        logv("", message, args);
    }

    public static void logv(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.v(TAG + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("V", TAG + tag, message));
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

    public static void logi(String message, Object... args) {
        logi("", message, args);
    }

    public static void logi(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.i(TAG + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("I", TAG + tag, message));
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
            logd(result[0], result[1]);
        }
    }

    public static void logd(String message, Object... args) {
        logd("", message, args);
    }

    public static void logd(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.d(TAG + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("D", TAG + tag, message));
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

    public static void logw(String message, Object... args) {
        logw("", message, args);
    }

    public static void logw(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.w(TAG + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("W", TAG + tag, message));
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

    public static void loge(String message, Object... args) {
        loge("", message, args);
    }

    public static void loge(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        android.util.Log.e(TAG + tag, message);

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("E", TAG + tag, message));
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

    public static void lognet(String tag, String message, Object... args) {
        if (mSettings.isDebug) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            android.util.Log.d(mSettings.netTag + "/" + tag, message);
        }

        if (mIsWriteToFile && mLogFile != null) {
            mLogFile.writeLog(getLogString("N", mSettings.netTag + "/" + tag, message));
        }
    }

    /**
     * 格式化打印Json数据，方便你阅读Logcat JSON数据
     */
    public static void json(String json) {
        if (mSettings.isDebug) {
            Log.d(XmlJsonParser.json(json));
        }
    }

    /**
     * 格式化打印xml数据
     */
    public static void xml(String xml) {
        if (mSettings.isDebug) {
            Log.d(XmlJsonParser.xml(xml));
        }
    }

    /**
     * 打印对象内部数据，包括但不限于List，String[]等等
     */
    public static void object(Object object) {
        if (mSettings.isDebug) {
            Log.d(ObjParser.parseObj(object));
        }
    }

    private static String[] formatMessage(String mesage) {
        String[] caller = getCaller();
        String[] formatMsg = new String[2];
        formatMsg[0] = caller[0];
        if (mesage.equals("")) {
            formatMsg[1] = caller[1] + "(";
        } else {
            formatMsg[1] = mesage + " ==> " + caller[1] + "(";
        }

        if (mSettings.showMethodLink) {
            formatMsg[1] += caller[2] + ":" + caller[3];
        }
        formatMsg[1] += ")";
        if (mSettings.showThreadInfo) {
            formatMsg[1] += getThreadName();
        }
        return formatMsg;
    }

    /**
     * get class name,method name and so on.
     * @return class name,method file name,line number
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
            for (StackTraceElement element : traceElements) {
                Log.logd(mSettings.appTag, element.toString());
            }
        }
    }

    public static void printStackTrace(Throwable e) {
        StackTraceElement[] traceElements = e.getStackTrace();
        if (traceElements != null) {
            for (StackTraceElement element : traceElements) {
                Log.loge(mSettings.appTag, element.toString());
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
