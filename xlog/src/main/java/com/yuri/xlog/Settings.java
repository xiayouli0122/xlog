package com.yuri.xlog;

/**
 * Log Setting
 * @author Yuri
 */
public class Settings {

    protected String appTag = "Yuri";
    protected String netTag = "YuriNet";

    protected boolean showMethodLink = true;

    protected boolean showThreadInfo = true;

    protected boolean isDebug = true;

    public static Settings getInstance() {
        return new Settings();
    }

    private Settings() {

    }

    /**
     * @deprecated see {@link Settings#hideThreadInfo()}
     */
    public Settings isShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }

    /**
     * @deprecated see {@link Settings#hideMethodLink()}
     */
    public Settings isShowMethodLink(boolean showMethodLink) {
        this.showMethodLink = showMethodLink;
        return this;
    }

    /**
     * default is show
     */
    public Settings hideMethodLink() {
        this.showMethodLink = false;
        return this;
    }

    /**
     * default is show
     */
    public Settings hideThreadInfo() {
        this.showThreadInfo = false;
        return this;
    }

    public Settings isDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }

    /**
     * set for common log tag,default is "Yuri"
     */
    public Settings setAppTag(String tag) {
        this.appTag = tag;
        return this;
    }

    /**
     * set for net log tag
     * default tag is "YuriNet"
     */
    public Settings setNetTag(String netTag) {
        this.netTag = netTag;
        return this;
    }
}
