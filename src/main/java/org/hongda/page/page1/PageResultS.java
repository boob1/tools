package org.hongda.page.page1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageResultS
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/9 19:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultS <T>{
    @JsonProperty("list")
    private List<T> list;

    @JsonProperty("pagePara")
    private PagePara pagePara;
}
