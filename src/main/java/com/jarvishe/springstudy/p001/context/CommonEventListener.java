package com.jarvishe.springstudy.p001.context;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommonEventListener {

    @EventListener
    public void listen(MyApplicationEvent event) {
        String name = event.getName();
        System.out.println("监听事件MyApplicationEvent，打印 name = " + name);
    }
}
