package org.hongda.page.page1;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/9 19:26
 **/
@Data
@TableName("user")
public class User {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;
}
