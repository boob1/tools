package org.hongda.repeatSubmit.service;

import org.hongda.repeatSubmit.UserDTO;
import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/8 10:34
 **/
@RestController
@RequestMapping("/user")
public class User22Controller {
    @Autowired
    IUser22Service userService;

    @PostMapping("/save")
    public Result saveUser(UserDTO dto){
        userService.saveUser(dto);
        return Result.success();
    }

}
