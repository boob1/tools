package org.hongda.tranceId.util;

import org.hongda.tranceId.constant.Constants;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName ThreadMdcUtil
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:29
 **/
public class ThreadMdcUtil {


    public static void setTranceIdIfAbsent() {
        if (MDC.get(Constants.TRANCE_ID) == null) {
            MDC.put(Constants.TRANCE_ID, TraceIdUtil.getTraceId());
        }
    }


    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTranceIdIfAbsent();


            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }


    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }


            setTranceIdIfAbsent();


            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
