package org.hongda.countingClicks.noRepeat;

import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ClicksCount
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/11 15:14
 **/
@RestController
public class ClicksCountController {
    @Autowired
    private ViewService viewService;

    @GetMapping("/getClicksCount")
    public Result getClicksCount() {
        return Result.success(viewService.getClicksCount());
    }

}
