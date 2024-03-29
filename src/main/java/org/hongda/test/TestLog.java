package org.hongda.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName TestLog
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/14 20:20
 **/
@Slf4j
public class TestLog {
    public static void main(String[] args) {
        log.info("info log");
        log.error("error log");
        log.debug("debug log");
        log.warn("warn log");
        log.trace("trace log");

    }
}
