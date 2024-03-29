package org.hongda.desensitization.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hongda.desensitization.Desensitization;
import org.hongda.desensitization.DesensitizationTypeEnum;

/**
 * @ClassName TestPojo
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/26 20:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPojo {
    private String userName;

    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @Desensitization(type = DesensitizationTypeEnum.MY_RULE, startInclude = 3, endExclude = 8)
    private String address;
}
