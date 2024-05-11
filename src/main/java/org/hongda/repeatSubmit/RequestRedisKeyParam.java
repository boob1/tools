package org.hongda.repeatSubmit;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @ClassName RequestRedisKeyParam
 * @Description 自定义分布式锁key的参数注解
 * @Author liuyibo
 * @Date 2024/5/8 8:59
 **/
@Target({java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestRedisKeyParam {

}
