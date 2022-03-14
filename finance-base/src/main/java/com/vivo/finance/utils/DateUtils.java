package com.vivo.finance.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zyk
 * @Description 时间处理类
 * @date 2022年03月02日
 */
public class DateUtils {

    /*默认日期格式*/
    private static final String DEFALUT_PATTERN = "yyyy-MM-dd";

    /**
     * 当前日期转字符串
     *
     * @param pattern
     * @return
     */
    public static String nowDateToStr(String pattern) {
        return dateToStr(new Date(), pattern);
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     */
    public static String dateToStr(Date date, String pattern) {
        if (date == null)
            return null;
        return new SimpleDateFormat(pattern).format(date);
    }
    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     */
    public static String dateToStr(Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat(DEFALUT_PATTERN).format(date);
    }

    /**
     * 字符串转日期
     *
     * @param pattern
     * @return
     */
    public static Date strToDate(String str, String pattern) throws ParseException {
        if (StringUtils.isBlank(str))
            return null;
        return new SimpleDateFormat(pattern).parse(str);
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 获取每天的开始时间 00:00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        dateStart.set(Calendar.MILLISECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 获取每天的开始时间 23:59:59:999
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        dateEnd.set(Calendar.MILLISECOND, 999);
        return dateEnd.getTime();
    }

    public static Date strToDate(String str) {
        try {
            return strToDate(str, DEFALUT_PATTERN);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期错误" + str, e);
        }
    }


    /**
     * @Description: 获取指定时间的当月的第一天
     * @author zyk
     * @date 2022/3/7 19:44
     * @param date
     */
    public static Date getMonthOneDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }
    public static Boolean isMonthOnDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (1==day){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        //System.out.println("获取当月第一天时间"+dateToStr(getMonthOneDay(new Date())));
        System.out.println("判断当前时间是否是1号"+isMonthOnDay(DateUtils.strToDate("2021-03-02")));
    }

}
