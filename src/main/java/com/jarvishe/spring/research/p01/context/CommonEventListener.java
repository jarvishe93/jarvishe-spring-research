package com.jarvishe.spring.research.p01.context;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 *
 * @author: heguoliang
 * @date: 2023/1/30
 */
@Component
public class CommonEventListener {

    @EventListener
    public void listen(MyApplicationEvent event) {
        String name = event.getName();
        System.out.println("监听事件MyApplicationEvent，打印 name = " + name);
    }
}
