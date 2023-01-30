package com.jarvishe.springstudy.p002.beanFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DefaultListableBeanFactory测试类
 *
 * @author: heguoliang
 * @date: 2023/1/30
 */
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
        // 需要给beanFactory（实际是给beanFactory里面的BeanDefinitionRegistry）添加一些增强器，作用为补充一些bean的定义
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        // 使这些增强器发挥作用
        for (BeanFactoryPostProcessor postProcessor : beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }

        // 查看当前beanFactory内的对象信息
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName : " + beanDefinitionName);
        }

        // 添加beanPostProcessors
        beanFactory.addBeanPostProcessors(beanFactory.getBeansOfType(BeanPostProcessor.class).values());

        // 如果需要提前准备单例bean
        beanFactory.preInstantiateSingletons();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 获取bean2
        System.out.println(beanFactory.getBean(Bean2.class));
        // 获取bean1
        System.out.println(beanFactory.getBean(Bean1.class));
        // 获取bean1里面的bean2
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());

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

        @Autowired
        private Bean2 bean2;

        private Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        public Bean2() {
            System.out.println("bean2初始化");
        }
    }
}
