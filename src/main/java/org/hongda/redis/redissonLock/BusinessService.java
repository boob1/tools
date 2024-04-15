package org.hongda.redis.redissonLock;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName BusinessService
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/15 10:13
 **/
@Service
public class BusinessService {
    // 业务逻辑方法
    public void doSomething() {
        String lockKey = "myLockKey";
        boolean isLocked = RedissonLockUtil.lock(lockKey, 5, TimeUnit.SECONDS);
        if (!isLocked) {
            System.out.println("获取锁失败，业务逻辑未执行");

        }

        try {

            Thread.sleep(1000);
            // 执行业务逻辑
            System.out.println("执行业务逻辑...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            RedissonLockUtil.unlock(lockKey);
        }
    }
}
