package org.hongda.springBootConstant.PropertySources;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @ClassName AppConfig
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/2 15:37
 **/
@Configuration
@PropertySources({
        @PropertySource("classpath:default.properties"),
        @PropertySource("classpath:custom.properties")})
public class MyAppConfig {
}
