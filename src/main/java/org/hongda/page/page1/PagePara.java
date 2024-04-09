package org.hongda.page.page1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PagePara
 * @Description 分页属性
 * @Author liuyibo
 * @Date 2024/4/9 19:12
 **/
@Data
@NoArgsConstructor
public class PagePara {
    @JsonProperty("nowPage")
    private Long nowPage;

    @JsonProperty("onePageCount")
    private Long onePageCount;

    @JsonProperty("dataCount")
    private Long dataCount;

    @JsonProperty("pageCount")
    private Long pageCount;

    @JsonProperty("startIndex")
    private Long startIndex;

    @JsonProperty("orderKey")
    private String orderKey;

    public PagePara(Long nowPage, Long onePageCount, Long dataCount, Long pageCount) {
        this.nowPage = nowPage;
        this.onePageCount = onePageCount;
        this.dataCount = dataCount;
        this.pageCount = pageCount;
    }
}
