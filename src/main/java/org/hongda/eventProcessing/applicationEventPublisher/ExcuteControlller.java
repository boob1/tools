package org.hongda.eventProcessing.applicationEventPublisher;

import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Controlller
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/3 10:14
 **/
@RestController
public class ExcuteControlller {
    @Autowired
    private MyService2 myService;

    @GetMapping("/execute")
    public Result getResult(){
        myService.performSomeAction();
        return Result.success();
    }

}
