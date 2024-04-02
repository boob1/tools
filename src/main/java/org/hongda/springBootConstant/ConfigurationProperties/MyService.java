package org.hongda.springBootConstant.ConfigurationProperties;

import org.hongda.springBootConstant.ConfigurationProperties.config.MyAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyService
 * @Description 注入引用
 * @Author liuyibo
 * @Date 2024/4/2 15:08
 **/
@Service
public class MyService {
    @Autowired
    private MyAppProperties myAppProperties;

    public Map<String,Object> doSomething() {
        String appName = myAppProperties.getName();
        String appVersion = myAppProperties.getVersion();

        Map<String,Object> map =new HashMap<String,Object>();
        // 使用配置属性进行操作
        map.put("AppName" , appName);
        map.put("AppVersion" , appVersion);
        return map;
    }
}
