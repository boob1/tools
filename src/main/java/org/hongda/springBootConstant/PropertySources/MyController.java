package org.hongda.springBootConstant.PropertySources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/2 15:42
 **/
@RestController
public class MyController {
    private final MyService1 myService;

    @Autowired
    public MyController(MyService1 myService) {
        this.myService = myService;
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return myService.getAppInfo();
    }
}
