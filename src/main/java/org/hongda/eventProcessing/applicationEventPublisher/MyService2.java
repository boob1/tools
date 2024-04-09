package org.hongda.eventProcessing.applicationEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @ClassName MyService
 * @Description 发布事件
 * @Author liuyibo
 * @Date 2024/4/2 19:30
 **/
@Service
public class MyService2 {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void performSomeAction() {
        // 创建并发布自定义事件
        MyCustomEvent event = new MyCustomEvent(this, "发布广播消息！");
        applicationEventPublisher.publishEvent(event);
    }
}
