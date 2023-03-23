package com.ppphuang.wechatapi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String FORMAT_PATTERN_YMD="yyyyMMdd";

    public static final String FORMAT_PATTERN_MD="MMdd";

    public static String getDateByFormat(Date date, String format) {
        String dateStr = "";
        if(date==null){
            return "";
        }
        try {
            if (format != null) {
                SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
                dateStr = simFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

}
