package org.hongda.tranceId.util;

/**
 * @ClassName TraceIdUtil
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:30
 **/

import org.hongda.tranceId.constant.Constants;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @description 获取tranceId方法
 * @date 2024年 03月 20日 14:01
 */
public class TraceIdUtil {



    /**
     * 获取当前请求的Trace ID。
     * 如果当前线程没有Trace ID，则生成一个新的。
     *
     * @return 当前请求的Trace ID
     */
    public static String getTraceId() {
        String tid = MDC.get(Constants.TRANCE_ID);
        if (tid == null) {
            // 生成新的Trace ID，这里可以是UUID、时间戳+随机数、递增ID等
            tid = UUID.randomUUID().toString().replace("-", "");
            MDC.put(Constants.TRANCE_ID, tid);
        }
        return tid;
    }


}
