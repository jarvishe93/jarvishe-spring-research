package com.jarvishe.springstudy.context;

import org.springframework.context.ApplicationEvent;

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
