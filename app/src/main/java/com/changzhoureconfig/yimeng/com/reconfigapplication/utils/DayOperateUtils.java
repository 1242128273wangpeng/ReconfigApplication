package com.changzhoureconfig.yimeng.com.reconfigapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class DayOperateUtils {
    public static SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date dsacDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int leftday = c.get(Calendar.DATE);
        c.set(Calendar.DATE, leftday - 1);
        return c.getTime();
    }

    public static String FormatDay(Date date) {
        String currdayStr = spf.format(date);
        return currdayStr;
    }

    public static Date inscreDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int rightday = c.get(Calendar.DATE);
        c.set(Calendar.DATE, rightday + 1);
        return c.getTime();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @return
     */
    public static int daysBetween(Calendar c1, Calendar c2) {
        long between_days = 0l;
        String s1 = spf.format(c1.getTime());
        String s2 = spf.format(c2.getTime());
        try {
            Date date1 = spf.parse(s1);
            Date date2 = spf.parse(s2);
            c1.setTime(date1);
            c2.setTime(date2);
            long time1 = c1.getTimeInMillis();
            long time2 = c2.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
            System.out.println("between_days-->" + between_days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @return
     */
    public static int daysBetweenByDate(Date date1, Date date2) {
        try {
            long between_days = 0l;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date1);
            c2.setTime(date2);
            String s1 = spf.format(c1.getTime());
            String s2 = spf.format(c2.getTime());
            Date tempdate1 = spf.parse(s1);
            Date tempdate2 = spf.parse(s2);
            c1.setTime(tempdate1);
            c2.setTime(tempdate2);
            long time1 = c1.getTimeInMillis();
            long time2 = c2.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
            System.out.println("between_days-->" + between_days);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
