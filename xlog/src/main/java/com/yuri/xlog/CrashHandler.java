package com.yuri.xlog;

import com.yuri.xlog.util.Util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = Log.mSettings.appTag + "CrashHandler";
    private static CrashHandler mInstance = new CrashHandler();
    private UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(thread, ex);

        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    private boolean handleException(Thread thread, Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String log = writer.toString();
        printWriter.close();
        Log.loge(TAG, log);

        String time = Util.getTime();
        LogFile logFile = new LogFile("error_log_" + time + ".txt");
        logFile.open();
        logFile.writeLog(log);
        logFile.close();
        
        LogcatSaver logcatSaver = new LogcatSaver("error_logcat_" + time + ".txt");
        logcatSaver.start();
        
        return true;
    }

}
