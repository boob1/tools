package org.hongda.repeatSubmit;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @ClassName RequestDebounceLockAspect
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/8 9:05
 **/
@Aspect
@Configuration
@Order(2)
public class RequestDebounceLockAspect {
    private RedissonClient redissonClient;

    @Autowired
    public RequestDebounceLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 对标注了@RequestDebounceLock注解的方法进行拦截，实现请求防抖和防重复提交的功能。
     * 通过使用Redisson分布式锁，确保同一时间只有一个请求可以执行，从而避免重复提交。
     *
     * @param joinPoint 切面连接点，表示被拦截的方法的执行点
     * @return 返回被拦截方法的执行结果
     * @throws RuntimeException 如果请求重复提交或者获取锁失败，则抛出异常
     */
    @Around("execution(public * *(..)) && @annotation(org.hongda.repeatSubmit.RequestDebounceLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        // 获取方法签名和方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取RequestDebounceLock注解，并校验prefix是否为空
        RequestDebounceLock requestLock = method.getAnnotation(RequestDebounceLock.class);
        if (StrUtil.isBlank(requestLock.prefix())) {
            throw new RuntimeException("RequestDebounceLock注解的prefix不能为空");
        }

        // 根据方法信息生成唯一的锁key
        final String lockKey = RequestKeyGenerator.getLockKey(joinPoint);
        // 使用Redisson获取分布式锁
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLock = false;

        try {
            // 尝试获取锁，如果获取失败，则认为请求已重复提交
            isLock = lock.tryLock();
            if (!isLock) {
                throw new RuntimeException("请求重复提交,请稍后再试");
            }
            // 获取到锁后，设置锁的过期时间
            lock.lock(requestLock.expire(), requestLock.timeUnit());

            // 执行被拦截的方法
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            // 无论如何都释放锁，确保锁的正确释放，避免死锁
            if (isLock && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


}
