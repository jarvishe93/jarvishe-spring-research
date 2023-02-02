package com.jarvishe.springstudy.p002;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

/**
 * 各种ApplicationContext的测试
 *
 * @author: heguoliang
 * @date: 2023/2/1
 */
public class ApplicationContextTest {

    public static void main(String[] args) {
        // testClassPathXmlApplicationContext();
        // testSelfReadClassPathXmlApplicationContext();
        // testFileSystemXmlApplicationContext();
        testAnnotationConfigApplicationContext();
    }

    static void testSelfReadClassPathXmlApplicationContext() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        System.out.println("beanFactory 初始 bean names");
        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("b01.xml"));
        // xmlBeanDefinitionReader.loadBeanDefinitions(new FileSystemResource("src/main/resources/b01.xml"));
        System.out.println("beanFactory XmlBeanDefinitionReader 读取之后的 bean names");
        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
    }

    static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("b01.xml");
        Arrays.stream(classPathXmlApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(classPathXmlApplicationContext.getBean(Bean2.class).getBean1());
    }

    static void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("src/main/resources/b01.xml");
        Arrays.stream(fileSystemXmlApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(fileSystemXmlApplicationContext.getBean(Bean2.class).getBean1());
    }

    static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(annotationConfigApplicationContext.getBean(Bean2.class).getBean1());
    }

    @Configuration
    static class AnnotationConfig {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        // todo 研究Bean1自动注入
        @Bean
        public Bean2 bean2(Bean1 bean1) {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }
    }

    static class Bean1 {

    }

    static class Bean2 {
        private Bean1 bean1;

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }

        public Bean1 getBean1() {
            return bean1;
        }
    }
}
