package com.piegroup.zzbm.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @// TODO: 2019/3/5 时间类
 */

public class TimeUtil {

    /**
     *
     * @// TODO: 2019/3/5  时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * @// TODO: 2019/3/5  日期格式字符串转换成时间戳
     *
     * @param date_str   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @return
     * @// TODO: 2019/3/5 获取当前的时间戳
     */
    public static String TimestampNow() {
        long time = System.currentTimeMillis();
        return String.valueOf(time / 1000);
    }

}
