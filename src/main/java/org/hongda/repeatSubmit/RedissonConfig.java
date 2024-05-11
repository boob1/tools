package org.hongda.repeatSubmit;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedissonConfig
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/8 10:50
 **/
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 设置你的Redis连接配置...
        config.useSingleServer()
                .setAddress("redis://47.96.234.82:6379").setPassword("123456");

        return Redisson.create(config);
    }
}
