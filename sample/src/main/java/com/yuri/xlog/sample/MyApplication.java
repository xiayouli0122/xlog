package com.yuri.xlog.sample;

import android.app.Application;

import com.yuri.xlog.CrashHandler;
import com.yuri.xlog.Log;
import com.yuri.xlog.Settings;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.initialize(
                Settings.getInstance()
                .isDebug(BuildConfig.DEBUG)
                .setAppTag("Yuri")
                .setNetTag("YuriNet")
        );

        if (BuildConfig.DEBUG) {
            CrashHandler.getInstance().init();
//            Log.startSaveToFile();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d();
//        Log.stopAndSave();
    }
}
