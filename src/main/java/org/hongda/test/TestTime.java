package org.hongda.test;

import org.hongda.utils.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName TestTime
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/11 19:26
 **/
public class TestTime {

    public static void main(String[] args) {
        // new Date()和Calendar.getInstance().getTime()
        DateUtil.testOldNewDate();

        // 获取新的时间方式
        DateUtil.testNewDate();

        // 旧的-Date转化String
        DateUtil.testDateToString(new Date(), "yyyy-MM-dd HH:mm:ss");

        // 新的-LocalDate转化String
        DateUtil.testNewDateToString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");

        // 测试旧的新增 addSecond、addMinute、addHour、addDay、addMonth、addYear
        DateUtil.addSecond(new Date(), 10);
        DateUtil.addMinute(new Date(), 10);
        DateUtil.addHour(new Date(), 10);
        DateUtil.addDay(new Date(), 10);
        DateUtil.addMonth(new Date(), 10);
        DateUtil.addYear(new Date(), 10);
        // ------------新的方式获取-添加秒、分、时、天、月、年------------------------------------------
        DateUtil.testAddSecond(LocalDateTime.now(), 10);
        DateUtil.testAddMinute(LocalDateTime.now(), 10);
        DateUtil.testAddHour(LocalDateTime.now(), 10);
        DateUtil.testAddDay(LocalDateTime.now(), 10);
        DateUtil.testAddMonth(LocalDateTime.now(), 10);
        DateUtil.testAddYear(LocalDateTime.now(), 10);
        // ----------------旧方式--获取当天星期几-----------------------------------------------
        DateUtil.testDateToWeek(new Date());

        // ----------------新方式--获取当天星期几-----------------------------------------------
        DateUtil.testNewDateToWeek(LocalDate.now());

        // ----------------旧方式--获取一天的开始时间-----------------------------------------------
        System.out.println(DateUtil.testDateToStartEnd(new Date()));

        // ----------------旧方式--获取一天的结束时间-----------------------------------------------
        System.out.println(DateUtil.getEndTimeOfDay(new Date()));

        // ----------------新方式--获取一天的开始时间-----------------------------------------------
        System.out.println(DateUtil.testNewstartTimeOfDay(LocalDateTime.now()));

        // ----------------新方式--获取一天的结束时间-----------------------------------------------
        System.out.println(DateUtil.testNewEndTimeOfDay(LocalDateTime.now()));

        // ----------------旧方式--比较时间-----------------------------------------------
        System.out.println(DateUtil.betweenStartAndEnd(new Date(), new Date(), new Date()));

        // ----------------新的--比较时间-----------------------------------------------
        System.out.println(DateUtil.betweenStartAndEnd(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
    }
}
