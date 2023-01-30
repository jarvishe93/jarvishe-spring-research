package com.jarvishe.springstudy.p002.beanFactory;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DefaultListableBeanFactoryTest {
    public static void main(String[] args) {
        // 测试 DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 给beanFactory添加bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Config.class);
        beanDefinitionBuilder.setScope("singleton");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
        // 为了使Bean1和Bean2也能添加进beanFactory里面，
        // 需要给beanFactory（实际是给beanFactory里面的BeanDefinitionRegistry）添加一些增强器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 使这些增强器发挥作用
        for (BeanFactoryPostProcessor postProcessor : beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
        // 查看当前beanFactory内的对象信息
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName : " + beanDefinitionName);
        }
    }

    @Configuration
    static class Config {
        @Bean("bean1")
        public Bean1 initBean1() {
            return new Bean1();
        }

        @Bean("bean2")
        public Bean2 initBean2() {
            return new Bean2();
        }

    }

    static class Bean1 {
        public Bean1() {
            System.out.println("bean1初始化");
        }
    }

    static class Bean2 {
        public Bean2() {
            System.out.println("bean2初始化");
        }
    }
}
