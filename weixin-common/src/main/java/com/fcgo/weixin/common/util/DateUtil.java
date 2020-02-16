/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * DateUtil.java
 * 
 * @author xiahan
 */
public abstract class DateUtil {
    private static final String DATE_FORMATE_PATTERN = "yyyy-MM-dd kk:mm:ss.SSSS";

    private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getCurrentDate(TimeZone zone, Locale aLocale) {
        return Calendar.getInstance(zone, aLocale).getTime();
    }

    public static Date getCurrentDate(TimeZone zone) {
        return Calendar.getInstance(zone).getTime();
    }

    public static Date getCurrentDate(Locale aLocale) {
        return Calendar.getInstance(aLocale).getTime();
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(dateStr);
    }

    /**
     * @param time
     * @param format
     * @return
     */
    public static String format(Date time, String format, Locale locale) {
        return new SimpleDateFormat(format, locale).format(time);
    }

    /**
     * 获取当前北京时间
     * 
     * @return
     * @throws ParseException
     * @author wangjinping
     */
    public static Date getBJCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMATE_PATTERN);
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        simpleDateFormat.setTimeZone(timeZone);
        String gmt08Date = simpleDateFormat.format(Calendar.getInstance(timeZone, Locale.CHINA).getTime());
        Date date = null;
        try {
            date = new SimpleDateFormat(DateUtil.DATE_FORMATE_PATTERN).parse(gmt08Date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getExpireTimeByDays(Date addDate, short shortValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        calendar.add(Calendar.DAY_OF_YEAR, shortValue + 1);
        return DateUtils.truncate(calendar.getTime(), Calendar.DATE);
    }

    public static Date getExpireTimeByMinutes(Date addDate, Integer shortValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        calendar.add(Calendar.MINUTE, shortValue);
        return calendar.getTime();
    }

    public static int getHours(Date date1, Date date2) {
        long date1Times = date1.getTime();
        long date2Times = date2.getTime();
        return (int) ((date1Times - date2Times) / (60 * 60 * 1000));
    }

    // public static void main(String[] args) throws ParseException {
    // System.out.println(":" + (new Long(0).equals(new Long(0))));
    // }
    /**
     * 时间精度，将上限时间调整为当天的最后时刻，列表搜索使用
     */
    public static Date[] endDateForSearch(Date[] date) {
        if (null == date) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        if (null == date[date.length - 1]) {
            return date;
        }
        cal.setTime(date[date.length - 1]);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.add(Calendar.MILLISECOND, -1);
        date[date.length - 1] = cal.getTime();
        return date;
    }

    public static Date endDateForSearch(Date date) {
        if (null == date) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.add(Calendar.MILLISECOND, -1);
        date = cal.getTime();
        return date;
    }

    /**
     * 如果第二个时间在第一个时间之前，交换两个时间
     * 
     * @param time
     * @return
     */
    public static Date[] exchangeTime(Date[] time) {
        if (null == time) {
            return null;
        }
        else if ((time.length < 2) || (null == time[1]) || (null == time[0])) {
        }
        else if (time[0].after(time[1])) {
            Date dateTemp = new Date();
            dateTemp = time[0];
            time[0] = time[1];
            time[1] = dateTemp;
        }
        return time;
    }

    /**
     * 两个时间之间相差距离多少天
     * 
     * @param one 时间参数 1：
     * @param two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            }
            else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            }
            else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = diff / (60 * 60 * 1000) - day * 24;
            min = diff / (60 * 1000) - day * 24 * 60 - hour * 60;
            sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            }
            else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = diff / (60 * 60 * 1000) - day * 24;
            min = diff / (60 * 1000) - day * 24 * 60 - hour * 60;
            sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    private static CountdownTime calculateIntervalTime(Date start, Date end) {
        if ((start == null) || (end == null)) {
            return null;
        }
        long between = start.getTime() - end.getTime();
        if (between <= 0) {
            return new CountdownTime();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = between / (60 * 60 * 1000) - day * 24;
        long minute = between / (60 * 1000) - day * 24 * 60 - hour * 60;
        long second = between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60;
        return new CountdownTime(day, hour, minute, second);
    }

    /**
     * 获取两个时间的间隔<br/>
     * 如果第一个时间比第二个时间早，则自动交换时间<br/>
     * 生成的时间格式为：d天H小时m分s秒
     * 
     * @param start
     * @param end
     * @return
     * @author liuw
     */
    public static String getIntervalTime(Date start, Date end) {
        return calculateIntervalTime(start, end).toCnString();
    }

    public static String getIntervalTime(Calendar start, Calendar end) {
        return calculateIntervalTime(start.getTime(), end.getTime()).toCnString();
    }

    public static String getIntervalTimeInEnglish(Date start, Date end) {
        return calculateIntervalTime(start, end).toEnString();
    }

    public static String getIntervalTimeInEnglish(Calendar start, Calendar end) {
        return calculateIntervalTime(start.getTime(), end.getTime()).toEnString();
    }

    public static Long getIntervalMillis(Calendar start, Calendar end) {
        return calculateIntervalTime(start.getTime(), end.getTime()).toMillis();
    }

    private static class CountdownTime {
        private long day;
        private long hour;
        private long minute;
        private long second;

        /**
		 *
		 */
        public CountdownTime() {
            this(0, 0, 0, 0);
        }

        /**
         * @param day
         * @param hour
         * @param minute
         * @param second
         */
        public CountdownTime(long day, long hour, long minute, long second) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public String toCnString() {
            StringBuilder builder = new StringBuilder();
            long[] timeArray = getTimeInArray();
            int index = getConcatIndex(timeArray);
            for (int length = timeArray.length; index < length; index++) {
                builder.append(timeArray[index]).append(timeNameArray[0][index]);
                if (index < length - 1) {
                    builder.append(" ");
                }
            }
            return builder.toString();
        }

        public String toEnString() {
            StringBuilder builder = new StringBuilder();
            long[] timeArray = getTimeInArray();
            int index = getConcatIndex(timeArray);
            for (int length = timeArray.length; index < length; index++) {
                builder.append(timeArray[index]).append(timeNameArray[1][index]);
                if (index < length - 1) {
                    builder.append(" ");
                }
            }
            return builder.toString();
        }

        public long toMillis() {
            long[] timeArray = getTimeInArray();
            return timeArray[3] * 1000 + timeArray[2] * 1000 * 60 + timeArray[1] * 1000 * 60 * 60 + timeArray[0] * 1000
                    * 60 * 60 * 24;
        }

        private long[] getTimeInArray() {
            return new long[]{day, hour, minute, second};
        }

        private int getConcatIndex(long[] timeArray) {
            int length = timeArray.length;
            for (int i = 0; i < length; i++) {
                if (timeArray[i] != 0) {
                    return i;
                }
            }
            return length - 1;
        }

        private String[][] timeNameArray = new String[][]{{" 天", " 小时", " 分", " 秒"},
                {" days", " hours", " minutes", " seconds"}};
    }

    /**
     * 判断两个时间段是否在同一个星期内
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean issameweekdates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subyear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subyear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        else if (1 == subyear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        else if (-1 == subyear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到本周周一的时间
     * 
     * @param nowTime 当前时间
     * @return
     */
    public String getMonday(Date nowTime) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(nowTime);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return sd.format(calendar.getTime());

    }

    /**
     * @param string
     * @param pattern
     * @return
     */
    /**
     * @param string
     * @param pattern
     * @return
     */
    public static Date getDateByStr(String dateTxt, String pattern) {
        SimpleDateFormat parser = new SimpleDateFormat(pattern);
        try {
            return parser.parse(dateTxt);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDaysAfter(Date addDate, short shortValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        calendar.add(Calendar.DAY_OF_YEAR, shortValue);
        return calendar.getTime();
    }

    public static Date getMinituesAfter(Date addDate, short shortValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        calendar.add(Calendar.MINUTE, shortValue);
        return calendar.getTime();
    }

    public static String format2yyyyMMdd(Date date) {
        return yyyy_MM_dd.format(date);
    }



    public static int getCurrentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh.mm aa 'EST' MMM d , yyyy", Locale.US);
        System.out.println(sdf.format(date));
    }
}
