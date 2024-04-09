package org.hongda.eventProcessing.applicationEventPublisher;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyEventListener
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/2 19:48
 **/
@Component
public class MyEventListener implements ApplicationListener<MyCustomEvent> {
    @Override
    public void onApplicationEvent(MyCustomEvent event) {
        // 处理自定义事件
        String eventData = event.getEventData();
        System.out.println("Received event with data: " + eventData);
    }
}
