package org.hongda.springBootConstant.ConfigurationProperties;

import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConstantController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/2 15:11
 **/
@RestController
public class ConstantController {

   @Autowired
   private MyService myService;
   @GetMapping("findConstant")
   public Result getConstant(){
       return Result.success(myService.doSomething());
   }
}
