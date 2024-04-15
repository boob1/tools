package org.hongda.redis.redisTool;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/10 17:17
 **/
@Configuration
public class RedisConfig {
    /*
     * Redis本身提供了一下一种序列化的方式：
     *   GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化
     *   Jackson2JsonRedisSerializer: 跟JacksonJsonRedisSerializer实际上是一样的
     *   JacksonJsonRedisSerializer: 序列化object对象为json字符串
     *   JdkSerializationRedisSerializer: 序列化java对象
     *   StringRedisSerializer: 简单的字符串序列化
     *
     *   默认缺点：JdkSerializationRedisSerializer
     *
     * 如果我们存储的是String类型，默认使用的是StringRedisSerializer
     * 这种序列化方式。如果我们存储的是对象，
     * 默认使用的是 JdkSerializationRedisSerializer，也就是Jdk的序列化方式
     *（通过ObjectOutputStream和ObjectInputStream实现，缺点是我们无法直观看到存储的对象内容）
     *
     *
     * 本方法作用：这个函数的作用是创建并配置一个能够处理字符串键和任意类型值的RedisTemplate Bean，
     * 用于在Spring应用程序中与Redis进行交互
     * @Date 17:19 2024/4/10
     * @Param
     * @return
     **/
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer); // key的序列化类型

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // ObjectMapper对象来定制JSON序列化的方式
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置属性的可见性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 启用类型信息
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value的序列化类型
        // 哈希键序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 哈希值序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 调用afterPropertiesSet方法来初始化RedisTemplate对象，并返回该对象
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
