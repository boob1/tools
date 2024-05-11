package org.hongda.repeatSubmit;

import lombok.Data;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/8 10:32
 **/
@Data
public class UserDTO {
    @RequestRedisKeyParam
    private String UserName;

    @RequestRedisKeyParam
    private String phone;
}
