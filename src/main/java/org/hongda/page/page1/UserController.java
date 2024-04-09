package org.hongda.page.page1;

import org.hongda.page.page1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description 测试分页查询
 * @Author liuyibo
 * @Date 2024/4/9 19:45
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/findAll")
    public PageResultS findAll(@RequestBody PagePara pagePara) {
        return userService.findAll(pagePara);
    }
}
