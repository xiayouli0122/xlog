package com.yuri.xlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yuri on 2016/5/31.
 */
public class Util {

    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }

}
