package com.jarvishe.spring.research;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * springboot启动类
 *
 * @author: heguoliang
 * @date: 2023/1/30
 */
@SpringBootApplication
public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("test");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.close();
        logger.info("关闭容器");
    }

}
