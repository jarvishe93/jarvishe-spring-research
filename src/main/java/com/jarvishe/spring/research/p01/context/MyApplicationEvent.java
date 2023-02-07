package com.jarvishe.spring.research.p01.context;

import org.springframework.context.ApplicationEvent;

/**
 * 事件器
 *
 * @author: jarvis
 * @date: 2023/1/30
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String name;

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
