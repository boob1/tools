package org.hongda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.hongda.tree.mapper,org.hongda.page.mapper")
public class HongDaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HongDaApplication.class, args);
    }
}