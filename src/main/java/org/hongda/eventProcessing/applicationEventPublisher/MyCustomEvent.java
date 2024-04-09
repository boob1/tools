package org.hongda.eventProcessing.applicationEventPublisher;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName MyCustomEvent
 * @Description 创建自定义事件
 * @Author liuyibo
 * @Date 2024/4/2 19:36
 **/
public class MyCustomEvent extends ApplicationEvent {
    private final String eventData;

    public MyCustomEvent(Object source, String eventData) {
        super(source);
        this.eventData = eventData;
    }

    public String getEventData() {
      return eventData;
    }
}
