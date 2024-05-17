package org.hongda.feignTry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @Author liuyibo
 * @Description 定义重试机制
 * @Date 11:37 2024/5/17
 * @Param
 * @return
 **/

@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Backoff {
    long delay() default 1000L;;
    long maxDelay() default 0L;
    double multiplier() default 0.00;;
}
