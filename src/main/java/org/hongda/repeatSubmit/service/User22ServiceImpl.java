package org.hongda.repeatSubmit.service;

import lombok.extern.slf4j.Slf4j;
import org.hongda.repeatSubmit.RequestDebounceLock;
import org.hongda.repeatSubmit.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/8 10:37
 **/
@Service
@Slf4j
public class User22ServiceImpl implements IUser22Service {
    @Override
    @RequestDebounceLock(prefix = "saveUser")
    public void saveUser(UserDTO dto) {
        log.info("保存用户信息{}", dto);
    }
}
