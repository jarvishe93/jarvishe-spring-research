package com.jarvishe.springstudy.p001.context;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * Context测试类
 *
 * @author: heguoliang
 * @date: 2023/1/30
 */
@SpringBootTest
class ContextTests {

    /**
     * ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
     */
    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void contextLoads() throws Exception {
        // context和beanFactory的关系
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        field.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) field.get(beanFactory);
        map.entrySet().stream()
                .filter(e -> e.getKey().startsWith("com"))
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
        // context功能1：国际化
        String msgKey = "hi";
        System.out.println(context.getMessage(msgKey, null, Locale.CHINA));
        System.out.println(context.getMessage(msgKey, null, Locale.ENGLISH));
        System.out.println();
        // context功能2：获取资源
        Resource[] resources = context.getResources("classpath*:*.properties");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        System.out.println();
        // context功能3：获取环境变量
        System.out.println(context.getEnvironment().getProperty("server.port"));
        System.out.println();
        // context功能4：发送事件
        context.publishEvent(new MyApplicationEvent(context, "图图哥哥"));
        System.out.println();
    }

}
