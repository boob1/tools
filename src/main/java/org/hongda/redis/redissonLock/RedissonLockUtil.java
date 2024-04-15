package org.hongda.redis.redissonLock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedissonLockUtil
 * @Description 分布式锁的各种方法
 * @Author liuyibo
 * @Date 2024/4/15 10:02
 **/
public class RedissonLockUtil {
    private static RedissonClient redissonClient;

    static {
        // 初始化 Redisson 客户端
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.96.234.82:6379");
        config.useSingleServer().setPassword("123456");
        // 根据 Config 创建 RedissonClient 实例
        redissonClient = Redisson.create(config);
    }

    // 获取锁
    public static boolean lock(String lockKey, long leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(0, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    // 释放锁
    public static void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    // 关闭 Redisson 客户端
    public static void shutdown() {
        if (redissonClient != null) {
            redissonClient.shutdown();
        }
    }
}
