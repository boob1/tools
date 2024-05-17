package org.hongda.feignTry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @Author liuyibo
 * @Description 定义最大的重试次数
 * @Date 11:38 2024/5/17
 * @Param
 * @return
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface FeignRetry {

    Backoff backoff() default @Backoff();
    int maxAttempt() default 3;
    Class<? extends Throwable>[] include() default {};
}
