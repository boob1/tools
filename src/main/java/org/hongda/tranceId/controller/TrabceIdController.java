package org.hongda.tranceId.controller;

import lombok.extern.slf4j.Slf4j;
import org.hongda.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TrabceIdController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/23 11:34
 **/
@RestController
@RequestMapping("/tranceId")
@Slf4j
public class TrabceIdController {

    @GetMapping("/insert")
    public Result insert() {
        return Result.success();
    }
}
