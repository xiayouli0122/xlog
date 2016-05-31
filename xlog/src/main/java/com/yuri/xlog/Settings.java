package com.yuri.xlog;

import android.util.Log;

/**
 * @author Yuri
 */
public class Settings {

    protected String appTag;
    protected String netTag;
    protected int methodOffset = 0;

    protected boolean showMethodLink = true;

    protected boolean showThreadInfo = false;

    protected boolean isDebug = true;

    public static Settings getInstance() {
        return new Settings();
    }

    private Settings() {

    }

    public Settings setMethodOffset(int methodOffset) {
        this.methodOffset = methodOffset;
        return this;
    }

    public Settings isShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }

    public Settings isShowMethodLink(boolean showMethodLink) {
        this.showMethodLink = showMethodLink;
        return this;
    }

    public Settings isDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }

    public Settings setAppTag(String tag) {
        this.appTag = tag + "/";
        return this;
    }

    public Settings setNetTag(String netTag) {
        this.netTag = netTag + "/";
        return this;
    }
}
