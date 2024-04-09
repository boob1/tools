package org.hongda.page.page1.service;

import org.hongda.page.page1.PagePara;
import org.hongda.page.page1.PageResultS;
import org.hongda.page.page1.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/9 19:30
 **/
public interface UserService {
    PageResultS<User> findAll(PagePara pagePara);
}
