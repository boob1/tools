package org.hongda.vo;

import lombok.Data;

/**
 * @ClassName UserVo
 * @Description 用户信息
 * @Author liuyibo
 * @Date 2024/3/18 9:39
 **/
@Data
public class UserVo {
    private Long userId;

    private String userName;

    private String account;

    private String password;
}
