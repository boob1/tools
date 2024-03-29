package org.hongda.desensitization.controller;

import org.hongda.desensitization.pojo.TestPojo;
import org.hongda.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestController
 * @Description 对象返回、集合返回、返回封装都可以
 * @Author liuyibo
 * @Date 2024/3/26 20:30
 **/
@RestController
public class TestController {
    @RequestMapping("/test")
    public TestPojo testDesensitization(){
        TestPojo testPojo = new TestPojo();
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        System.out.println(testPojo);
        return testPojo;
    }

    @RequestMapping("/testList")
    public List<TestPojo> testDesensitizatioList(){
        List<TestPojo> testPojos = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        System.out.println(testPojo);
        testPojos.add(testPojo);
        return testPojos;
    }
    @RequestMapping("/testListResut")
    public Result testListResut(){
        List<TestPojo> testPojos = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        System.out.println(testPojo);
        testPojos.add(testPojo);
        return Result.success(testPojos);
    }
}
