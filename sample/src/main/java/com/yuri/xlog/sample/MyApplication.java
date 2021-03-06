package com.yuri.xlog.sample;

import android.app.Application;
import android.util.Log;

import com.yuri.xlog.XLogCrashHandler;
import com.yuri.xlog.XLog;
import com.yuri.xlog.Settings;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        XLog.initialize(
//                Settings.getInstance()
//                .isDebug(BuildConfig.LOG_DEBUG)
//                .setAppTag("Yuri")
//        );

        if (BuildConfig.DEBUG) {
            XLogCrashHandler.getInstance().init();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        XLog.d();
    }
}
