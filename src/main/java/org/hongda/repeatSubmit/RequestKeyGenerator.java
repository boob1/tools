package org.hongda.repeatSubmit;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @ClassName RequestKeyGenerator
 * @Description 生成锁key的值
 * @Author liuyibo
 * @Date 2024/5/8 9:40
 **/
public class RequestKeyGenerator {

    /**
     * 生成锁的键名。
     * 根据方法参数或对象字段的注解 RequestRedisKeyParam 来构建锁的键名。
     * 键名格式为: 前缀 + 分隔符 + 参数值(或字段值)。
     * 如果方法参数或对象字段没有注解 RequestRedisKeyParam，则忽略该参数或字段。
     *
     * @param joinPoint AOP的连接点，代表被拦截的方法。
     * @return 构建好的锁键名字符串。
     */
    public static String getLockKey(ProceedingJoinPoint joinPoint) {
        // 获取方法签名和Method对象
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        RequestDebounceLock requestLock = method.getAnnotation(RequestDebounceLock.class);
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取Method对象上所有的参数注解
        final Parameter[] parameters = method.getParameters();
        StringBuilder sb = new StringBuilder();
        // 遍历参数，寻找注解RequestRedisKeyParam，并拼接键名
        for (int i = 0; i < parameters.length; i++) {
            final RequestRedisKeyParam keyParam = parameters[i].getAnnotation(RequestRedisKeyParam.class);
            if (keyParam == null) {
                continue;
            }
            sb.append(requestLock.delimiter()).append(args[i]);
        }

        // 如果上述拼接为空，则尝试根据参数对象的字段注解拼接键名
        if (StrUtil.isEmpty(sb.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object arg = args[i];
                // 遍历参数对象的字段，寻找注解RequestRedisKeyParam，并拼接键名
                final Field[] fields = arg.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final RequestRedisKeyParam annotation = field.getAnnotation(RequestRedisKeyParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    sb.append(requestLock.delimiter()).append(ReflectionUtils.getField(field, arg));
                }
            }
        }
        // 返回最终构建的锁键名
        return StrUtil.format("{}{}", requestLock.prefix(), sb);
    }

}
