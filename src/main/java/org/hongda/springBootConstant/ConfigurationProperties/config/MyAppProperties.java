package org.hongda.springBootConstant.ConfigurationProperties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MyAppProperties
 * @Description @ConfigurationProperties 注解指定了一个前缀 "myapp"，
 * 这意味着配置文件中的属性键应以 "myapp" 开头
 * @Author liuyibo
 * @Date 2024/4/2 15:01
 **/
@Data
@ConfigurationProperties(prefix =  "myapp")
public class MyAppProperties {
    private String name;
    private String version;

}
