package com.jarvishe.spring.research.p03.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/5
 */
@Component
public class LifeCycleBean {
    Logger logger = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        logger.info("LifeCycleBean 执行构造方法");
    }

    @Autowired
    public void autowiredMethod(@Value("${JAVA_HOME}") String javaHome) {
        logger.info("LifeCycleBean autowiredMethod 注入javaHome={}", javaHome);
    }

    @PostConstruct
    public void init() {
        logger.info("LifeCycleBean init 执行初始化，和postProcessBeforeInitialization方法会冲突");
    }


    @PreDestroy
    public void preDestroy() {
        logger.info("LifeCycleBean preDestroy 执行销毁前");
    }
}
