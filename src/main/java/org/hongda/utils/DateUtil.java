package org.hongda.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/11 19:28
 **/
public class DateUtil {


    // 1.旧的获取new Date();Calendar.getInstance().getTime()
    public static void testOldNewDate() {
        Date date = new Date();
        Date time = Calendar.getInstance().getTime();
        System.out.println("new Date()......" + date);
        System.out.println("Calendar.getInstance().getTime()......" + time);
    }

    // 1.新的获取时间
    public static void testNewDate() {
        // Instant获取一个时间点
        Instant now = Instant.now();
        System.out.println("Instant.now()..." + now);

        // 如果需要获取具体的日期和时间（年月日时分秒）
        LocalDateTime newLocalDateTime = LocalDateTime.now();
        System.out.println("获取具体的日期和时间LocalDateTime.now()...." + newLocalDateTime);

        // 获取特定的时区交互
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("特定的时区交互ZonedDateTime.now()......" + zonedDateTime);

        // Instant转Date
        Date date = Date.from(now);
        System.out.println("Instant转Date  Date.from(now)..." + date);

        // 如果需要与java.util.timestamp进行交互
        java.sql.Timestamp timestamp = java.sql.Timestamp.from(now);
        System.out.println("java.sql.Timestamp.from(now)..." + timestamp);

    }

    //2.旧的-Date转化String
    public static void testDateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        System.out.println("DateToString old....." + dateFormat.format(date));
    }

    // 3.新的-String转化Date
    public static void testNewDateToString(LocalDateTime date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        System.out.println("testNewDateToString.new.." + date.format(dateTimeFormatter));
    }

    // b. addSecond、addMinute、addHour、addDay、addMonth、addYear
    // addSecond
    public static void addSecond(Date date, int second) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.SECOND, second);
        System.out.println("addSecond.new.." + instance.getTime());
    }

    //   addMinute
    public static void addMinute(Date date, int minute) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE, minute);
        System.out.println("addMinute.new.." + instance.getTime());
    }

    // addHour
    public static void addHour(Date date, int hour) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, hour);
        System.out.println("addHour.new.." + instance.getTime());
    }

    // addDay
    public static void addDay(Date date, int day) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, day);
        System.out.println("addDay.new.." + instance.getTime());
    }

    // addMonth
    public static void addMonth(Date date, int month) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH, month);
        System.out.println("addMonth.new.." + instance.getTime());
    }

    // addYear
    public static void addYear(Date date, int year) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, year);
        System.out.println("addYear.new.." + instance.getTime());
    }

    //--- ----------------------------------------------------
    // 新的方式：b. addSecond、addMinute、addHour、addDay、addMonth、addYear
    public static void testAddSecond(LocalDateTime date, int second) {
        date.plusSeconds(second);
        System.out.println("testAddSecond.new.." + date);
    }

    // addMinute
    public static void testAddMinute(LocalDateTime date, int minute) {
        date.plusMinutes(minute);
        System.out.println("testAddMinute.new.." + date);
    }

    // addHour
    public static void testAddHour(LocalDateTime date, int hour) {
        date.plusHours(hour);
        System.out.println("testAddHour.new.." + date);
    }

    // addDay
    public static void testAddDay(LocalDateTime date, int day) {
        date.plusDays(day);
        System.out.println("testAddDay.new.." + date);
    }

    // addMonth
    public static void testAddMonth(LocalDateTime date, int month) {
        date.plusMonths(month);
        System.out.println("testAddMonth.new.." + date);
    }

    // addYear
    public static void testAddYear(LocalDateTime date, int year) {
        date.plusYears(year);
        System.out.println("testAddYear.new.." + date);
    }

    // 旧方式获取当天星期几
    public static final String[] WEEK_DAY_OF_CHINESE = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static void testDateToWeek(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        System.out.println(WEEK_DAY_OF_CHINESE[instance.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    // 新方式  获取当天星期几
    public static void testNewDateToWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(WEEK_DAY_OF_CHINESE[dayOfWeek.getValue() % 7]);

    }

    //原来的方式获取一天的开始时间和结束时间
    public static Date testDateToStartEnd(Date date) {
        if (date == null) {
            return null;
        } else {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
            LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
            return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    //原来的方式获取一天的结束时间
    public static Date getEndTimeOfDay(Date date) {
        if (date == null) {
            return null;
        } else {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
            LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
            return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    // 新的方式获取一天的开始时间；

    public static LocalDateTime testNewstartTimeOfDay(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate().atStartOfDay();
        }
    }

    // 新的方式获取一天的结束时间
    public static LocalDateTime testNewEndTimeOfDay(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate().atTime(LocalTime.MAX);
        }
    }

    // 旧方式----时间比较
    public static boolean betweenStartAndEnd(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        return date.after(begin) && date.before(end);
    }

    //新的方式----时间比较
    public static Boolean betweenStartAndEnd(LocalDateTime nowTime, LocalDateTime beginTime, LocalDateTime endTime) {
        return nowTime.isAfter(beginTime) && nowTime.isBefore(endTime);
    }


}


