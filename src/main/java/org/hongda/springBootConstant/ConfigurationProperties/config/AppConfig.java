package org.hongda.springBootConstant.ConfigurationProperties.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AppConfig
 * @Description @EnableConfigurationProperties 注解用于启用对 MyAppProperties 类的配置属性绑定
 * @Author liuyibo
 * @Date 2024/4/2 15:05
 **/
@Configuration
@EnableConfigurationProperties(MyAppProperties.class)
public class AppConfig {
}
