package org.hongda.redis;

import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/10 14:46
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/save")
    public Result save(String key, String value) {
        redisUtil.set(key, value);
        return Result.success();
    }
}
