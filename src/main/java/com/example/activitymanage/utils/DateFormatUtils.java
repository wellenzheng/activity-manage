package com.example.activitymanage.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-27
 */


public class DateFormatUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Calendar calendar = Calendar.getInstance();

    public static Date fromString(String s) throws ParseException {
        return dateFormat.parse(s);
    }

    public static String get0Clock(Date date) {
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        return dateFormat.format(calendar.getTime());
    }

    public static String get12Clock(Date date) {
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                12, 0, 0);
        return dateFormat.format(calendar.getTime());
    }

    public static String get24Clock(Date date) {
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        return dateFormat.format(calendar.getTime());
    }
}
