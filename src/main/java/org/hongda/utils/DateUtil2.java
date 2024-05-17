package org.hongda.utils;

import com.nascent.base.utils.DateUtils;
import com.nascent.base.utils.StringUtils;
import com.nascent.ecrp.sg.common.exception.SgBaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 */
@Slf4j
public class DateUtil2 {

    public static final long MILLIS_PER_SECOND = 1000L;
    public static final long MILLIS_PER_MINUTE = 60000L;
    public static final long MILLIS_PER_HOUR = 3600000L;
    public static final long MILLIS_PER_DAY = 86400000L;
    /**
     * 时区
     */
    public static final ZoneId zoneId = ZoneId.systemDefault();

    /**
     * 日期格式
     */
    public interface DATE_PATTERN {
        String YYYY = "yyyy";
        String MM = "MM";
        String DD = "dd";
        String HHMMSS = "HHmmss";
        String E = "E";
        String HH_MM_SS = "HH:mm:ss";
        String HH_MM = "HH:mm";
        String YYYYMM = "yyyyMM";
        String YYYY_MM = "yyyy-MM";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String yyyymmddhhmmsssss = "yyyy-MM-dd-HH-mm-ss";
        String YYYY_MM_DD_HH_MM_SS_XXX_UTC = "yyyy-MM-dd'T'HH:mm:ssXXX";
        String YYYY_MM_DD_HH_MM_SS_SSSXX_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    }

    /**
     * 日期单位 // LJX 20121101 Add
     */
    public interface DATE_UNIT {
        String STR_DAY = "天";
        String STR_HOUR = "小时";
        String STR_MINUTE = "分钟";
        String STR_SECOND = "秒";
        String STR_MILLISECOND = "毫秒";

        long MILLIS_PER_SECOND = 1000L;
        long MILLIS_PER_MINUTE = 60000L;
        long MILLIS_PER_HOUR = 3600000L;
        long MILLIS_PER_DAY = 86400000L;
    }

    public enum TimeType {
        DAY("day"),
        HOUR("hour"),
        MINUTE("minute"),
        SECOND("second"),
        MILLISECOND("millisecond")
        ;

        TimeType(String value) {
            this.value = value;
        }

        private String value;
        public String getValue() {
            return value;
        }
    }

    /**
     * Date 类型 转 LocalDate
     * @param date  日期
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDate();
    }
    /**
     * Date 类型 转 LocalDate
     * @param date  日期
     * @return
     */
    public static LocalDateTime dateToLocalDatetime(Date date) {
        if(Objects.isNull(date)){
            return null;
        }
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDateTime();
    }
    /**
     *  LocalDate类型 转 Date
     * @param localDateTime  日期
     * @return
     */
    public static Date localDatetimeToDate(LocalDateTime localDateTime) {
        if(Objects.isNull(localDateTime)){
            return null;
        }
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date   要加减前的时间，如果不传，则为当前日期
     * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
     *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    /**
     * 对指定的日期对象，增加（减少）指定的毫秒数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliSecond(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的秒数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的分数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMiunte(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的小时数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的日期数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的日期数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(String date, int amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return add(sdf.parse(date), Calendar.DATE, amount);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对指定的日期对象，增加（减少）指定的月数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的月数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(String date, int amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            return add(sdf.parse(date), Calendar.MONTH, amount);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对指定的日期对象，增加（减少）指定的年数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 格式化日期
     *
     * @param date    要转换的日期
     * @param pattern 转换格式
     * @return 转换后的字符串
     */
    public static final String format(Object date, String pattern) {
        if (null == date) {
            return null;
        }
        if (null == pattern) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将当前时间转化为指定格式DEFAULT_DATE_FORMAT的字符串
     *
     * @return
     */
    public static String formatDate() {
        return (formatDate(now(), DATE_PATTERN.YYYY_MM_DD));
    }

    /**
     * @return String
     * @throws
     * @title 返回格式化的当前时间
     * @description <br>
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String formatNow() {
        return formatDateTime(now());
    }

    /**
     * @param format
     * @return
     */
    public static String formatDate(String format) {
        return (formatDate(now(), format));
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为DEFAULT_DATE_FORMAT格式的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为DEFAULT_DATE_FORMAT格式的字符串
     *
     * @param date 日期对象 秒级时间戳
     * @return
     */
    public static String formatDate(Long date) {
        return formatDate(new Date(date * 1000), DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为字符串
     *
     * @param date    日期对象
     * @param pattern 格式字符串，默认格式为：yyyy-MM-dd
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = DATE_PATTERN.YYYY_MM_DD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return (sdf.format(date));
    }

    /**
     * 将当前系统时间转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatDateTime() {
        return (formatDate(now(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 将当前系统时间转化为指定格式yyyy-MM-dd-HH-mm-ss的字符串
     *
     * @return
     */
    public static String formatTimeToString() {
        return (formatDate(now(), DATE_PATTERN.yyyymmddhhmmsssss));
    }

    /**
     * 将当前系统时间转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatDateTime(String format) {
        return (formatDate(now(), format));
    }

    /**
     * 将日期对象转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDateTime(Date date) {
        return (formatDate(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 从系统当前时间获取时间信息，转化为指定格式DEFAULT_TIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatTime() {
        return (formatDate(now(), DATE_PATTERN.HH_MM_SS));
    }

    /**
     * 从指定的日期对象中，获取指定格式DEFAULT_TIME_FORMAT的时间字符串
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return (formatDate(date, DATE_PATTERN.HH_MM_SS));
    }

    /**
     * @param date
     * @return
     */
    public static String formatShortDateTime(Date date) {
        return formatDate(date, DATE_PATTERN.YYYY_MM_DD_HH_MM);
    }

    /**
     * 格式化时间戳，转化为日期格式：MILLIS_PER_DAY {@link DATE_PATTERN}
     *
     * @param datetime
     * @return
     */
    public static String formatLongDate(long datetime) {
        return formatDate(new Date(datetime));
    }

    /**
     * 格式化long型的时间
     *
     * @param datetime
     * @return
     */
    public static String formatLongDateTime(long datetime) {
        return formatDateTime(new Date(datetime));
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为DEFAULT_6_DATE_FORMAT格式的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDateToYearMonth(Date date) {
        return formatDate(date, DATE_PATTERN.YYYY_MM);
    }

    /**
     * 把一个日期 格式化成 yyyy年MM月dd日HH时mm分ss秒
     *
     * @param date 日期
     * @return yyyy年MM月dd日HH时mm分ss秒
     */
    public static String formatChineseTime(Date date) {
        String dateStr = "";
        if (date != null) {
            //yyyy-MM-dd HH:mm:ss
            dateStr = formatDateTime(date);
        } else {
            dateStr = formatDateTime();
        }
        dateStr = dateStr.replaceFirst("-", "年");
        dateStr = dateStr.replaceFirst("-", "月");
        dateStr = dateStr.replaceFirst(" ", "日");
        dateStr = dateStr.replaceFirst(":", "时");
        dateStr = dateStr.replaceFirst(":", "分");
        dateStr = dateStr + "秒";
        return dateStr;
    }

    /**
     * 将时间戳转为时间类型
     * @param time
     * @return
     */
    public static String formatTimestampToDatetime(Long time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        return  format.format(time);
    }

    public static List<String> findDates(String begin, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dBegin = sdf.parse(begin);
            Date dEnd = sdf.parse(end);
            List<Date> lDate = new ArrayList();
            lDate.add(dBegin);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            // 测试此日期是否在指定日期之后
            while (dEnd.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }
            List<String> dateStrList = new ArrayList<>();
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
            for (Date date : lDate) {
                dateStrList.add(sdfs.format(date));
            }
            return dateStrList;
        }catch (Exception e) {
            throw new SgBaseRuntimeException("日期错误");
        }

    }

    /**
     * 周六周日判断
     *
     * @param dateString DEFAULT_DATE_FORMAT格式
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String dateString) {
        Date date = parseDate(dateString);
        boolean weekend = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            weekend = true;
        }
        return weekend;
    }

    /**
     * 判断year,month是否比当前月份靠后
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isAfterCurrent(String year, String month) {
        Date date1 = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(year + "-" + month + "-01");
        Date current = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(com.nascent.ecrp.sg.common.utils.DateUtil.formatDate(now(), "yyyy-mm") + "-01");
        return Objects.requireNonNull(date1).after(Objects.requireNonNull(current));
    }

    /**
     * 判断year,month是否比当前月份靠前
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isBeforeCurrent(String year, String month) {
        Date date1 = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(year + "-" + month + "-01");
        Date current = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(com.nascent.ecrp.sg.common.utils.DateUtil.formatDate(now(), "yyyy-MM") + "-01");
        return Objects.requireNonNull(date1).before(Objects.requireNonNull(current));
    }

    /**
     * 将分隔符为“-”的日期字符串转换成日期
     *
     * @param datestr 日期字符串
     * @return 日期
     */
    private static Calendar toCalendar(String datestr) {
        String[] strs = datestr.split("-");
        int year = 0;
        int month = 1;
        int date = 0;
        if (strs.length >= 3) {
            year = Integer.parseInt(strs[0]);
            month = Integer.parseInt(strs[1]);
            date = Integer.parseInt(strs[2]);
        } else if (strs.length == 2) {
            year = Integer.parseInt(strs[0]);
            month = Integer.parseInt(strs[1]);
        } else {
            year = Integer.parseInt(strs[0]);
        }
        return new GregorianCalendar(year, month - 1, date);
    }

    /**
     * 日期型字符串转日期
     *
     * @param str     日期型字符串
     * @param pattern 转换格式
     * @return 转换后的日期
     */
    public static final Date parse(String str, String pattern) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern).parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串转化为默认格式DEFAULT_DATETIME_FORMAT的时间对象
     *
     * @param datetime
     * @return
     */
    public static Date parseDateTime(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        if ((datetime == null) || ("".equals(datetime))) {
            return null;
        } else {
            try {
                return formatter.parse(datetime);
            } catch (ParseException e) {
                return parseDate(datetime);
            }
        }
    }

    /**
     * 将字符串转化为默认格式DEFAULT_DATETIME_FORMAT的时间对象
     *
     * @param datetime
     * @return
     */
    public static Date parseDateTimeUTC(String datetime) {
        if (StringUtils.isEmpty(datetime)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS_XXX_UTC);
        try {
            return formatter.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转化为指定格式DEFAULT_DATE_FORMAT的日期对象
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD);

        if ((date == null) || ("".equals(date))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 将字符串转化为指定格式DEFAULT_DATE_FORMAT的日期对象
     *
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if ((date == null) || ("".equals(date))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 用于导购工作统计、门店工作统计时间格式化
     *
     * @param date
     * @return
     */
    public static String parseString(String date, String pattern) {
        SimpleDateFormat sim;
        if (pattern != null) {
            sim = new SimpleDateFormat(pattern);
        } else {
            sim = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        }

        if (date == null) {
            return null;
        } else {
            return sim.format(DateUtil.parseDate(date));
        }
    }

    /**
     * 将字符串转化为指定格式DEFAULT_DATE_FORMAT的日期对象
     *
     * @param time
     * @return
     */
    public static Date parseTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN.HH_MM_SS);
        if ((time == null) || ("".equals(time))) {
            return null;
        } else {
            try {
                return formatter.parse(time);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 获取某天的23:59:59
     * @param str 格式yyyy-MM-dd
     * @return 转换后的日期 yyyy-MM-dd 23:59:59
     */
    public static final Date parseDateEndTime(String str) {
        return parseDateTime(str + " 23:59:59");
    }

    /**
     * 获取某天的00:00:00
     * @param str 格式yyyy-MM-dd
     * @return 转换后的日期 yyyy-MM-dd 00:00:00
     */
    public static final Date parseDateStartTime(String str) {
        return parseDateTime(str + " 00:00:00");
    }

    public static final String parseDate(Date time) {
        return formatDate(time) + " 00:00:00";
    }

    /**
     * 获取某个时间段内所有的日期字符串
     *
     * @param dfrom 起始日期
     * @param dto   结束日期
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     * @throws Exception
     */
    public static List getDateList(String dfrom, String dto) throws Exception {
        List list = new ArrayList();
        list.add(dfrom);
        if (dfrom.equals(dto)) {
            return list;
        }
        Calendar c = toCalendar(dfrom);
        while (true) {
            c.add(Calendar.DATE, 1);
            String newDate = new java.sql.Date(c.getTimeInMillis()).toString();
            if (newDate.equals(dto)) {
                list.add(newDate);
                break;
            }
            list.add(newDate);
        }
        return list;
    }

    /**
     * 获取某个时间段内所有的日期字符串
     *
     * @param dfrom 起始日期
     * @param dto   结束日期
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     */
    public static List getDateList2(String dfrom, String dto) {
        List list = new ArrayList();
        list.add(dfrom);
        if (dfrom.equals(dto)) {
            return list;
        }
        Calendar c = toCalendar(dfrom);
        while (true) {
            c.add(Calendar.DATE, 1);
            String newDate = new java.sql.Date(c.getTimeInMillis()).toString();
            if (newDate.equals(dto)) {
                //list.add(newDate);
                break;
            }
            list.add(newDate);
        }
        return list;
    }

    public static List<String> getDateListByMonth(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    public static List<String> getDateListByYear(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化为年

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.YEAR, 1);
        }

        return result;
    }

    public static List<String> getHourList(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月日时分秒

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DATE), min.get(Calendar.HOUR_OF_DAY), min.get(Calendar.SECOND));

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DATE), max.get(Calendar.HOUR_OF_DAY), max.get(Calendar.SECOND));

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(curr.get(Calendar.HOUR_OF_DAY) + "");
            curr.add(Calendar.HOUR, 1);
        }

        return result;
    }

    /**
     * 获取指定年内的所有的日期字符串
     *
     * @param year 指点年度
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     * @throws Exception
     */
    public static List getDateList4Year(String year) throws Exception {
        return getDateList(year + "-01-01", year + "-12-31");
    }

    /**
     * 根据日期获取是周几
     *
     * @param dateString DEFAULT_DATE_FORMAT格式
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day(String dateString) {
        Date date = parseDate(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        // 一周中的第几天(国外周日是第一天,所以需要减1)
        int n = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (n < 0) {
            n = 0;
        }
        return weekDays[n];
    }

    public Date getDateByYearMonth(String year, String month) {
        String dateString = null;
        if (month.length() == 1) {
            month = "0" + month;
        }
        dateString = year + "-" + month + "-" + "01";
        return null;
    }

    /**
     * 指定毫秒数表示的日期
     *
     * @param millis 毫秒数
     * @return 指定毫秒数表示的日期
     */
    public static final Date getDate(long millis) {
        return new Date(millis);
    }

    /**
     * 获取日期中的年
     *
     * @param date 日期
     * @return 年份
     */
    public static String getYear(Date date) {
        DateFormat f_year = new SimpleDateFormat("yyyy");
        return f_year.format(date);
    }

    /**
     * 根据字符串获取年
     *
     * @param dateString "yyyy-mm-dd"格式
     * @return
     */
    public static String getYear(String dateString) {
        Date date = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(dateString);
        return com.nascent.ecrp.sg.common.utils.DateUtil.getYear(date);
    }

    /**
     * 获取日期中的月
     *
     * @param date 日期
     * @return 月份
     */
    public static String getMonth(Date date) {
        DateFormat f_month = new SimpleDateFormat("MM");
        return f_month.format(date).toString();
    }

    /**
     * 获取日期中的月日
     *
     * @param date 日期
     * @return 月日MM-dd
     */
    public static String getMonthAndDay(Date date) {
        DateFormat f_month = new SimpleDateFormat("MM-dd");
        return f_month.format(date).toString();
    }

    /**
     * 根据字符串获取月
     *
     * @param dateString "yyyy-mm-dd"格式
     * @return
     */
    public static String getMonth(String dateString) {
        Date date = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(dateString);
        return com.nascent.ecrp.sg.common.utils.DateUtil.getMonth(date);
    }

    /**
     * 获取日期中天
     *
     * @param date 日期
     * @return 天
     */
    public static String getDay(Date date) {
        DateFormat f_day = new SimpleDateFormat("dd");
        return f_day.format(date).toString();
    }

    /**
     * 获取日期中的星期
     *
     * @param date 日期
     * @return 星期
     */
    public static String getWeek(Date date) {
        DateFormat f_week = new SimpleDateFormat("EEEEEEE");
        return f_week.format(date).toString();
    }

    /**
     * 获取日期中的时间
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        DateFormat f_time = new SimpleDateFormat("HH时mm分 ss秒");
        return f_time.format(date).toString();
    }

    /**
     * @description: 将Date转换为时间戳
     * @param dateStr
     * @return: long
     * @author: YaNan.Zhang
     * @date: 2019/3/4
     */
    public static Long getTime(String dateStr) {
        Date date = DateUtils.parseDate(dateStr);
        return date.getTime() / 1000;
    }

    /**
     * 把"yyyy-mm-dd"格式转换为"yyyy年mm月dd日"
     *
     * @param dateString
     * @return
     */
    public static String getChineseDateString(String dateString) {
        Date date = com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(dateString);
        DateFormat f_ch_date = new SimpleDateFormat("yyyy年MM月dd日");
        return f_ch_date.format(date).toString();
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把10位毫秒数着成时间
     */
    public static String getTimeDateString(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        return fm.format(times * 1000);
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把13位毫秒数着成时间
     */
    public static String getTimesDateString(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        return fm.format(times);
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把毫秒数着成时间
     */
    public static Date getTimeDateDate(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        return parseDateTime(fm.format(times * 1000));
    }

    /**
     * 当前时间距指定时间几天
     *
     * @param date
     * @return
     */
    public static long getRemainDay(Date date) {
        try {
            long diff = date.getTime() - com.nascent.ecrp.sg.common.utils.DateUtil.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (Exception ex) {
            log.error("时间工具处理异常", ex);
        }
        return 0;
    }

    /**
     * 两个时间相距多少天
     *
     * @return
     */
    public static long getRemainDay(Date startDate, Date endDate) {
        try {
            long diff = endDate.getTime() - startDate.getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (Exception ex) {
            log.error("时间工具处理异常", ex);
        }
        return 0;
    }

    /**
     * 当前时间距指定时间几小时
     *
     * @param date
     * @return
     */
    public static long getRemainHour(Date date) {
        try {
            long diff = date.getTime() - com.nascent.ecrp.sg.common.utils.DateUtil.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffHours;
        } catch (Exception ex) {
            log.error("时间工具处理异常", ex);
        }
        return 0;
    }


    public static long getDiff(Date date) {
        try {
            long diff = date.getTime() - com.nascent.ecrp.sg.common.utils.DateUtil.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diff;
        } catch (Exception ex) {
            log.error("时间工具处理异常", ex);
        }
        return 0;
    }


    /**
     * 当前时间距指定时间几分钟
     *
     * @param date
     * @return
     */
    public static long getRemainMinutes(Date date) {
        try {
            long diff = date.getTime() - com.nascent.ecrp.sg.common.utils.DateUtil.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffMinutes;
        } catch (Exception ex) {
            log.error("时间工具处理异常", ex);
        }
        return 0;
    }

    public static String getDateTimeByMs(long milliseconds) {
        return getDateTimeByMs(milliseconds, TimeType.MILLISECOND.getValue());
    }

    public static String getDateTimeByMs(long milliseconds, String timeType) {
        StringBuffer sb = new StringBuffer();
        long day = Math.abs(milliseconds / DATE_UNIT.MILLIS_PER_DAY);
        long hours = Math.abs((milliseconds % DATE_UNIT.MILLIS_PER_DAY) / DATE_UNIT.MILLIS_PER_HOUR);
        long minutes = Math.abs((milliseconds % DATE_UNIT.MILLIS_PER_DAY % DATE_UNIT.MILLIS_PER_HOUR) / DATE_UNIT.MILLIS_PER_MINUTE);
        long s = Math.abs((milliseconds % DATE_UNIT.MILLIS_PER_DAY % DATE_UNIT.MILLIS_PER_HOUR % DATE_UNIT.MILLIS_PER_MINUTE) / DATE_UNIT.MILLIS_PER_SECOND);
        long ms = Math.abs((milliseconds % DATE_UNIT.MILLIS_PER_DAY % DATE_UNIT.MILLIS_PER_HOUR % DATE_UNIT.MILLIS_PER_MINUTE % DATE_UNIT.MILLIS_PER_SECOND));

        boolean isContainMinute = false;
        boolean isContainMillisecond = false;
        boolean isCOntainSecond = false;

        if (timeType.equalsIgnoreCase(TimeType.MINUTE.getValue())) {
            isContainMinute = true;
        } else if (timeType.equalsIgnoreCase(TimeType.SECOND.getValue())) {
            isCOntainSecond = true;
        } else if (timeType.equalsIgnoreCase(TimeType.MILLISECOND.getValue())) {
            isContainMillisecond = true;
        }

        if (milliseconds < 0) {
            sb.append(" - ");
        }

        if (day > 0) {
            sb.append(String.valueOf(day) + DATE_UNIT.STR_DAY);
        }
        if (hours > 0) {
            sb.append(String.valueOf(hours) + DATE_UNIT.STR_HOUR);
        }
        if (isContainMinute) {
            if (minutes > 0) {
                sb.append(String.valueOf(minutes) + DATE_UNIT.STR_MINUTE);
            }
        } else {
            if (isCOntainSecond) {
                if (minutes > 0) {
                    sb.append(String.valueOf(minutes) + DATE_UNIT.STR_MINUTE);
                }
                if (s > 0) {
                    sb.append(String.valueOf(s) + DATE_UNIT.STR_SECOND);
                }
            } else {
                if (isContainMillisecond) {
                    if (minutes > 0) {
                        sb.append(String.valueOf(minutes) + DATE_UNIT.STR_MINUTE);
                    }
                    if (s > 0) {
                        sb.append(String.valueOf(s) + DATE_UNIT.STR_SECOND);
                    }
                    if (ms > 0) {
                        sb.append(String.valueOf(ms) + DATE_UNIT.STR_MILLISECOND);
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * 获取两日期的相隔小时
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     */
    public static double getBetweenHours(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1d;
        }
        DecimalFormat df = new DecimalFormat("0.0");
        long diff = dateEnd.getTime() - dateBegin.getTime();
        double diffDays = (double) diff / (60 * 60 * 1000);
        return Double.valueOf(df.format(diffDays));
    }

    /**
     * 获取两日期的相隔毫秒
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long getBetweenMs(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1;
        }

        long diff = dateEnd.getTime() - dateBegin.getTime();
        return diff / 1000;
    }

    /**
     * @param dateEnd 结束时间
     * @param dateBegin 起始时间
     * @description: 获取两日期的相隔秒
     * @return: long
     * @author: ZhiMin.Mo
     * @date: 21:52 2021/10/31
     */
    public static long getBetweenSecond(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1;
        }

        long diff = dateEnd.getTime() - dateBegin.getTime();
        return diff / 1000;
    }

    /**
     * 获取两日期的相隔小时(无精确值)
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     */
    public static double getBetweenHours2(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1d;
        }
        long diff = dateEnd.getTime() - dateBegin.getTime();
        double diffDays = diff / (double) (60 * 60 * 1000);
        return diffDays;
    }

    /**}}
     * 获取两日期的相隔时间(只算天的部分)
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long getBetweenDays(Date startDate, Date endDate) {
        AssertUtil.assertTrue(null != startDate && null != endDate, "开始时间&结束时间均不能为空");
        LocalDate start = dateToLocalDate(startDate);
        LocalDate end = dateToLocalDate(startDate);
        return end.toEpochDay() - start.toEpochDay();
    }

    /**
     * 根据日期获取是周几
     *
     * @param date DEFAULT_DATE_FORMAT格式
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        // 一周中的第几天(国外周日是第一天,所以需要减1)
        int n = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (n < 0) {
            n = 0;
        }
        return weekDays[n];
    }

    /**
     * 取默认最小时间
     *
     * @returnDate
     */
    public static Date getMinDate() {
        return parseDate("1970-01-01 00:00:00", DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @return Date
     * @title:获取当前日期，时分秒都为0
     * @throws:
     * @description:获取当前日期，时分秒都为0 <pre>
     * 业务逻辑描述：获取当前日期，时分秒都为0
     * </pre>
     */
    public static Date getDateNoTime() {
        return com.nascent.ecrp.sg.common.utils.DateUtil.parseDate(com.nascent.ecrp.sg.common.utils.DateUtil.formatDate());
    }

    public static Date getMonthFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    public static Date getMonthLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取指定日期年的最后一天
     *
     * @param date
     * @return
     */
    public static Date getYearLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取指定日期年的第一天
     *
     * @param date
     * @return
     */
    public static Date getYearFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getMonthFisrt(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getMonthLast(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 获取日期的前一天
     */
    public static Date getPreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获得近一周的开始时间和结束时间
     * @return
     */
    public static Map<String, String> getDaySevenRange(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> condition=new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.HOUR_OF_DAY,24);
        condition.put("endDate",df.format(calendar.getTime()));
        calendar.set(Calendar.HOUR_OF_DAY,-168);
        condition.put("startDate",df.format(calendar.getTime()));
        return condition;
    }

    /**
     * 获取当前时间 yyyyMMdd
     *
     * @return
     */
    public static String getCurrentNumTime() {
        return getNumTimeByDate(new Date());
    }

    /**
     * 获取时间 yyyyMMdd
     *
     * @return
     */
    public static String getNumTimeByDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYYMMDD);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取时间 yyyy-MM-dd
     *
     * @return
     */
    public static Date getCurrentDay() {
     return parseDate(String.valueOf(LocalDate.now()));
    }
    /**
     * 获取两个日期区间内的日期值
     *
     * @param begin1
     * @param end1
     * @param dateFormate
     * @param dateType    1:年   2：月  5：天
     * @return
     * @throws ParseException
     */
    public static List<String> getBetweenDates(String begin1, String end1, String dateFormate, int dateType) throws ParseException {
        try {
            Date begin = StringUtils.isBlank(begin1) ? new Date() : DateUtils.parseDate(begin1, dateFormate);
            Date end = StringUtils.isBlank(end1) ? new Date() : DateUtils.parseDate(end1, dateFormate);
            List<String> result = new ArrayList<String>();
            String respFormate = DATE_PATTERN.YYYYMMDD;

            Calendar beginCal = Calendar.getInstance();
            beginCal.setTime(begin);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(end);

            if (Calendar.DATE == dateType) {
            } else if (Calendar.MONTH == dateType) {
                respFormate = DATE_PATTERN.YYYYMM;
                // 获取开始日期月第一天
                beginCal.set(Calendar.DAY_OF_MONTH, 1);
                // 获取结束日期月最后一天
                endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (Calendar.YEAR == dateType) {
                respFormate = DATE_PATTERN.YYYY;
                // 获取开始日期月第一天
                beginCal.set(Calendar.DAY_OF_YEAR, 1);
                // 获取结束日期月最后一天
                endCal.set(Calendar.DAY_OF_YEAR, endCal.getActualMaximum(Calendar.DAY_OF_YEAR));
            }
            while (beginCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
                result.add(DateFormatUtils.format(begin, respFormate));
                if (Calendar.DATE == dateType) {
                    beginCal.add(Calendar.DATE, 1);
                } else if (Calendar.MONTH == dateType) {
                    beginCal.add(Calendar.MONTH, 1);
                } else if (Calendar.YEAR == dateType) {
                    beginCal.add(Calendar.YEAR, 1);
                }
                begin = beginCal.getTime();
            }
            return result;
        } catch (ParseException e) {
            throw e;
        }
    }

    public static String getMonthNum(Date date) {
        String num = format(date, "MM");
        return num != null && num.startsWith("0") ? num.substring(1) : num;
    }
    public static String getMonthNum(String date) {
        String num;
        if (date.length() <= 7) {
            num = format(parse(date, "yyyy-MM"), "MM");
        } else {
            num = format(parseDate(date), "MM");
        }
        return num != null && num.startsWith("0") ? num.substring(1) : num;
    }

    /**
     * @return 当日:yyyy-MM-dd 00:00:00 型的日期
     */
    public static Date getDayStart(Date date) {
        return parseDate(formatDate(date));
    }
    /**
     * 获取当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return format(new Date(), "yyyy");
    }
    public static int getLengthOfMonth(Date date) {
        return Integer.parseInt(getDayNum(addDay(addMonth(getYearAndMonth(date), 1), -1)));
    }
    /**
     * 间隔天数，只跟日期跨度有关，与间隔时分秒无关
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 两个日期间隔天数
     */
    public static final Integer getDayBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        long intervalMillis = end.getTimeInMillis() - start.getTimeInMillis();
        return (int) (intervalMillis / (60 * 60 * 24 * 1000L));
    }

    /**
     * 查询日期间隔，若毫秒间隔超过T-1天小于T天的，记为T-1天，若大于等于T天的，记为T天
     *
     * @param startDate  开始时间
     * @param endDate  结束时间
     * @return {@link long}
     * @author huangyuye
     */
    public static final long getDayBetween2(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 1000 / 60 /60 / 24;
    }

    /**
     * 根据天数拆分日期
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static List<List<Date>> splitDatePerDay(Date startTime, Date endTime) {
        List<List<Date>> dateList = new ArrayList<>();
        long daysBetween = DateUtil.getDayBetween(startTime, endTime);

        Date startTemp;
        Date endTemp;
        for (long i = 0; i <= daysBetween; i++) {
            if (i == 0) {
                startTemp = startTime;
            } else {
                startTemp = getStartOfDay(DateUtil.addDay(startTime, (int) i));
            }
            if (i == daysBetween) {
                endTemp = endTime;
            } else {
                endTemp = getEndOfDay(DateUtil.addDay(startTime, (int) i));
            }
            List<Date> list = new ArrayList<>();
            Collections.addAll(list, startTemp, endTemp);
            dateList.add(list);
        }
        return dateList;
    }

    /**
     * 获取某天凌晨0点0分0秒
     *
     * @return 获取某天凌晨0点0分0秒
     */
    public static Date getStartOfDay(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(null == startDate ? new Date() : startDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某天晚上23点59分59秒
     *
     * @return 某天晚上23点59分59秒
     */
    public static Date getEndOfDay(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(null == endDate ? new Date() : endDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    //获取一个月的起始时间
    public static Date getFirstDayDateOfMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    //获取一个月的最后一天
    public static Date getLastDayOfMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    /**
     * 获取传入日期的天数值
     */
    public static String getDayNum(Date date) {
        String dayNum = format(date, "dd");
        return dayNum != null && dayNum.startsWith("0") ? dayNum.substring(1) : dayNum;
    }

    /**
     * 获取传入日期的天数值
     */
    public static String getDayNum(String date) {
        String dayNum = format(parseDate(date), "dd");
        return dayNum != null && dayNum.startsWith("0") ? dayNum.substring(1) : dayNum;
    }

    /**
     * @return 当日:yyyy-MM-01 00:00:00 型的日期
     */
    public static Date getYearAndMonth(Date date) {
        return parse(format(date, "yyyy-MM"), "yyyy-MM");
    }

    /**
     * @return 当日:yyyy-MM-01 00:00:00 型的日期
     */
    public static Date getYearAndMonth(Long time) {
        Date date = new Date(time);
        return parse(format(date, "yyyy-MM"), "yyyy-MM");
    }

    /**
     * @return 当日:yyyy-MM-01 00:00:00 型的日期
     */
    public static Date getYearAndMonth(String date) {
        return parse(date, "yyyy-MM");
    }

    /**
     * 系统时间的毫秒数
     *
     * @return 系统时间的毫秒数
     */
    public static long getMilliseconds() {
        return System.currentTimeMillis();
    }

    /**
     * 获取系统当前日期对象
     *
     * @return
     */
    public static Date now() {
        return (new Date());
    }

    /**
     * @description: 获取近period的日期数组
     * 如period=7，则获取近七天日期（yyyy-MM-dd），包含当天
     * @param period 近几天
     * @return: java.lang.String[]
     * @author: ZhiMin.Mo
     * @date: 9:55 2020/10/13
     */
    public static String[] getPeriod(int period){
        int index = 0;
        Date today = new Date();
        String[] times = new String[period];
        do{
            times[index++] = DateUtil.formatDate(DateUtil.addDay(today, 1 - period));
            period--;
        }while (period > 0);
        return times;
    }
    /**
     * 转换日期显示
     *
     * @param date 日期
     * @return 日期显示例: 2020年01月10日 17:33
     */
    public static String getDateStr(Date date) {
        DateFormat f_moon = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return f_moon.format(date);
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1]?[0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author: XinYu.Zhang
     * @description: 获取某一个日期 的下一个周几 例: { 获取当前日期下一个周三 参数: 3,new Date() }
     *               如果今天周一  传入参数为 (1,new Date()) 则会返回今天的日期 因为今天是距周一最近的周一
     * @param: weekValue (weekValue 为0 是返回当前日期 例如今天周一 返回最近的一个周一 就是今天。。。) 周几 1-7
     * @param: date (时间为null默认当前时间) 基于这个日期获取的
     * @param: format 格式化格式  为空返回 "yyyy-MM-dd HH:mm:ss" 格式
     * @return: java.util.Date 返回计算后的日期结果
     * @date: 2022/8/22 17:28
     */
    public static Date getNextWeekDay(int weekValue,Date date,String format){
        date = date == null ? new Date() : date;
        weekValue = Integer.parseInt(weekValue + "");
        // 设置当前日期
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1 || week == 0) {
            week = (week == 1 ? 7 : 6);
        } else {
            week -= 1;
        }
        // 取距离当前日期最近的周日与当前日期相差的天数
        // (count：相差的天数。正数：之后的周日，负数：之前的周日)
        int count = 0;
        // 传入的周几和当前周几相同则不做处理 直接查询当天的
        if (week != weekValue) {
            // 当前日期和设置的周几不一致
            count = Math.abs(weekValue - week);
        }
        // 获取距离当前日期最近的周日日期
        aCalendar.add(Calendar.DAY_OF_WEEK, count);
        Date time = aCalendar.getTime();
        if (StringUtils.isNotEmpty(format)){
            // 参数不为空 则需要返回格式化后的时间
            String formatDate = formatDate(time, format);
            return parseDate(formatDate,format);
        }
        return time;
    }

    public static Date getNextWeekDay(int weekValue,Date date){
        return getNextWeekDay(weekValue,date,null);
    }

    /**
     * 判断两个日期是否是同一年的同一个月
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 是否同一个月
     */
    public static boolean isSameMonth(Date startDay, Date endDay) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDay);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDay);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameYear && isSameMonth;
    }

    /**
     * 获取两个日期间的天数集合
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 天数的集合
     */
    public static List<Integer> getBetweenDayList(Date startDay, Date endDay) {
        List<Integer> dayList = new ArrayList<>();
        for (List<Date> dateList : DateUtil.splitDatePerDay(startDay, endDay)) {
            dayList.add(Integer.parseInt(getDay(dateList.get(0))));
        }
        return dayList;
    }

    public static void main(String[] args) {
        Date yesterday = DateUtil.addDay(DateUtil.getCurrentDay(), -55);
        System.out.println(formatDate(yesterday));
        System.out.println(DateUtil.getDayNum(yesterday));
    }
}


