
package com.heartofdead.base.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static final String TAG = "TimeUtils";

    /**yyyyMMddHHmmss */
    public static final String FORMAT_Y_TO_S = "yyyyMMddHHmmss";

    /*yyyy-MM-dd HH:mm:ss */
    public static final String FORMAT_Y_TO_S_EN = "yyyy-MM-dd HH:mm:ss";

    /**：yyyy-MM-dd */
    public static final String FORMAT_Y_TO_D = "yyyy-MM-dd";

    public static final String FORMAT_YMD = "yyyyMMdd";

    public static final String FORMAT_HMS = "HHmmss";

    public static final String FORMAT_H_TO_MIN = "HH:mm";

    public static String FORMAT_Y_M_D = "yyyy/MM/dd/ HH:mm:ss";
    public static String FORMAT_H_M_S = "HH:mm:ss";

    private TimeUtils(){
        throw new AssertionError();
    }

    /**
     * @param time
     * @param fromFormat
     * @param toFormat
     * @return
     * change a date string from the fromFormat to toFormat
     */
    public static String formatDate(String time, String fromFormat,
                                    String toFormat) {
        if (TextUtils.isEmpty(time)) {
            return null;
        } else if (TextUtils.isEmpty(fromFormat)
                || TextUtils.isEmpty(toFormat)) {
            return time;
        }

        String dateTime = time;


        String dataStr = null;
        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
            dataStr = newFormat.format(oldFormat.parse(dateTime));
        } catch (ParseException e) {
            Log.w(TAG, "date format failed: time=" + time + ", fromFormat="
                    + fromFormat + ", toFormat=" + toFormat, e);
            return time;
        }

        return dataStr;
    }

    public static String formatDate(String time, String toFormat) {
        if (time == null || "".equals(time.trim()))
            return "";

        String fromFormat = null;
        if (time.length() == 6) {
            fromFormat = "HHmmss";
        } else if (time.length() == 8) {
            fromFormat = "yyyyMMdd";
        } else if (time.length() == 14) {
            fromFormat = "yyyyMMddHHmmss";
        } else {
            return time;
        }

        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
            return newFormat.format(oldFormat.parse(time));
        } catch (ParseException e) {
            Log.w(TAG, "date format failed: time=" + time + ", fromFormat="
                    + fromFormat + ", toFormat=" + toFormat, e);
            return time;
        }
    }

    public static String formatDate(long time, String toFormat)  throws ParseException {
        Date date = new Date(time);
        SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
        return newFormat.format(date);
    }

    /**
     * @return
     * get current date string meeting formatStr
     */
    public static String getCurrentDate(String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    /**
     * @return
     *  format date according to toFormat
     */
    public static String formatDate(Date date, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }


}
