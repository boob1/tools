package org.hongda.redis.redissonLock;

import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BusinessController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/15 10:15
 **/
@RestController
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @GetMapping("/doSomething")
    public Result doSomething() {
        businessService.doSomething();
        return Result.success();
    }
}
