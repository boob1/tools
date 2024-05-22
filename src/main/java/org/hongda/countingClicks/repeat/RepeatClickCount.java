package org.hongda.countingClicks.repeat;

import cn.hutool.core.util.StrUtil;
import org.hongda.vo.Result;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RepeatClickCount
 * @Description 点一次计算一次的点击量
 * @Author liuyibo
 * @Date 2024/5/17 11:22
 **/
@Service
public class RepeatClickCount {
    private final static String SMALL_STORE_CLICK = "smallStoresStoriesReadModel:";
    @Autowired
    private RedissonClient redissonClient;


    public Result addAppClickVolume(String id) {
        String lockKey = StrUtil.format("{}{}", SMALL_STORE_CLICK, id);
        RLock lock = redissonClient.getLock(lockKey);
        long currentClicks = 0;
        try {
            boolean isLock = lock.tryLock(10, TimeUnit.SECONDS);
            if (!isLock) {
                return Result.success("点击太频繁稍后重试！");
            }
            String lockKey1 = StrUtil.format("{}{}", SMALL_STORE_CLICK+"1", id);
            RAtomicLong clickCounter = redissonClient.getAtomicLong(lockKey1);
            clickCounter.incrementAndGet();
            // 获取当前点击量
            currentClicks = clickCounter.get();

            // smallStoresStoriesReadModelRepository.updateReadingVolumeById(id, currentClicks);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return Result.success(currentClicks);
    }
}
