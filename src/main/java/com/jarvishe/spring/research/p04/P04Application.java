package com.jarvishe.spring.research.p04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 研究常见几种后处理器的功能
 *
 * @author: jarvis
 * @date: 2023/2/7
 */
public class P04Application {
    public static final Logger logger = LoggerFactory.getLogger(P04Application.class);

    public static void main(String[] args) {
        // 干净的容器
        logger.info("》》》》》》》》》》》创建一个干净的容器");
        GenericApplicationContext context = new GenericApplicationContext();

        // 注册几个bean
        logger.info("》》》》》》》》》》》注册Bean41、Bean42、Bean43");
        context.registerBean("bean41", Bean41.class);
        context.registerBean("bean42", Bean42.class);
        context.registerBean("bean43", Bean43.class);
        context.registerBean("bean44", Bean44.class);
        // 注册AutowiredAnnotationBeanPostProcessor
        logger.info("》》》》》》》》》》》注册 AutowiredAnnotationBeanPostProcessor");
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        logger.info("》》》》》》》》》》》注册 CommonAnnotationBeanPostProcessor");
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        logger.info("》》》》》》》》》》》注册 ConfigurationPropertiesBindingPostProcessor");
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());
        // 解析字符串值，需要额外添加一个处理类
        logger.info("》》》》》》》》》》》添加 ContextAnnotationAutowireCandidateResolver");
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // refresh
        logger.info("》》》》》》》》》》》context调用refresh方法");
        context.refresh();

        logger.info("Bean44:{}", context.getBean(Bean44.class));

        logger.info("》》》》》》》》》》》context调用close方法");
        context.close();
    }
}
