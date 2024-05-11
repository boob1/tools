package org.hongda.repeatSubmit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RequestDebounceLock
 * @Description 自定义防抖注解
 * @Author liuyibo
 * @Date 2024/5/8 8:51
 **/
@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestDebounceLock {
    /**
     * redis锁前缀（接口方法名）
     */
    String prefix() default "";

    /**
     * redis过期时间 默认2秒
     */
    int expire() default 2;

    /**
     * redis过期时间单位，默认单位秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * redis  key分隔符
     * 默认@
     */
    String delimiter() default "@";


}
