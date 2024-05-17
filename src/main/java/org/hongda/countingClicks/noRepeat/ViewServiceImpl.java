package org.hongda.countingClicks.noRepeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

/**
 * @ClassName ViewServiceImpl
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/11 15:15
 **/
@Service
public class ViewServiceImpl implements ViewService{
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public int getClicksCount() {
       /* redisTemplate.hincry("clicksCount", "count", 1);*/

        redisTemplate.opsForHyperLogLog().add("clicksCount", Arrays.asList(UUID.randomUUID()));

        return redisTemplate.opsForHyperLogLog().size("clicksCount").intValue();
    }
}
