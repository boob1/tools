package org.hongda;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("org.hongda.tree.mapper,org.hongda.page.page1.mapper")
public class HongDaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HongDaApplication.class, args);
        log.info("启动成功.......................................>>>>>>>>>>>>>>>>");
    }
}