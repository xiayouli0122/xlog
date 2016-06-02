package com.yuri.xlog;

/**
 * @author Yuri
 */
public class Settings {

    protected String appTag;
    protected String netTag;

    protected boolean showMethodLink = true;

    protected boolean showThreadInfo = true;

    protected boolean isDebug = true;

    public static Settings getInstance() {
        return new Settings();
    }

    private Settings() {

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
        this.appTag = tag;
        return this;
    }

    public Settings setNetTag(String netTag) {
        this.netTag = netTag;
        return this;
    }
}
