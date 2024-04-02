package org.hongda.springBootConstant.PropertySources;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName MyService
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/2 15:40
 **/
@Service
public class MyService1 {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public String getAppInfo() {
        return "App Name: " + appName + ", App Version: " + appVersion;
    }
}
