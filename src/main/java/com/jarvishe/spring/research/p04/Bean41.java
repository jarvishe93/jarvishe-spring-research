package com.jarvishe.spring.research.p04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/7
 */
public class Bean41 {

    public static final Logger logger = LoggerFactory.getLogger(Bean41.class);
    private Bean42 bean42;
    private Bean43 bean43;
    private String home;

    @Autowired
    public void setBean42(Bean42 bean42) {
        logger.info("》》》》》》》》》》》@Autowired生效 setBean42:{}", bean42);
        this.bean42 = bean42;
    }

    @Resource
    public void setBean43(Bean43 bean43) {
        logger.info("》》》》》》》》》》》@Resource生效 setBean43:{}", bean43);
        this.bean43 = bean43;
    }

    @Autowired
    private void setJavaHome(@Value("${JAVA_HOME}") String javaHome) {
        logger.info("》》》》》》》》》》》@Value生效 javaHome:{}", javaHome);
        home = javaHome;
    }

    @PostConstruct
    public void init() {
        logger.info("》》》》》》》》》》》@PostConstruct生效");
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("》》》》》》》》》》》@PreDestroy生效");
    }

    @Override
    public String toString() {
        return "Bean41{" +
                "bean42=" + bean42 +
                ", bean43=" + bean43 +
                ", home='" + home + '\'' +
                '}';
    }
}
