// package com.jarvishe.spring.research.p002;
//
// import org.springframework.beans.factory.support.DefaultListableBeanFactory;
// import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
// import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
// import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.context.support.FileSystemXmlApplicationContext;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.web.servlet.DispatcherServlet;
// import org.springframework.web.servlet.mvc.Controller;
//
// import java.util.Arrays;
//
// /**
//  * 各种ApplicationContext的测试
//  *
//  * @author: heguoliang
//  * @date: 2023/2/1
//  */
// public class ApplicationContextTest {
//
//     public static void main(String[] args) {
//         // testClassPathXmlApplicationContext();
//         // testSelfReadClassPathXmlApplicationContext();
//         // testFileSystemXmlApplicationContext();
//         // testAnnotationConfigApplicationContext();
//         testAnnotationConfigServletWebServerApplicationContext();
//     }
//
//     /**
//      * 手动扫描classpath下的xml文件，添加bean定义
//      */
//     static void testSelfReadClassPathXmlApplicationContext() {
//         DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//         System.out.println("beanFactory 初始 bean names");
//         Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
//         XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//         xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("b01.xml"));
//         // xmlBeanDefinitionReader.loadBeanDefinitions(new FileSystemResource("src/main/resources/b01.xml"));
//         System.out.println("beanFactory XmlBeanDefinitionReader 读取之后的 bean names");
//         Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
//     }
//
//     /**
//      * 经典容器，基于classpath下的xml文件创建
//      */
//     static void testClassPathXmlApplicationContext() {
//         ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("b01.xml");
//         Arrays.stream(classPathXmlApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//         System.out.println(classPathXmlApplicationContext.getBean(Bean2.class).getBean1());
//     }
//
//     /**
//      * 经典容器，基于filepath下的xml文件创建
//      */
//     static void testFileSystemXmlApplicationContext() {
//         FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("src/main/resources/b01.xml");
//         Arrays.stream(fileSystemXmlApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//         System.out.println(fileSystemXmlApplicationContext.getBean(Bean2.class).getBean1());
//     }
//
//     /**
//      * 经典容器，基于java配置类创建
//      */
//     static void testAnnotationConfigApplicationContext() {
//         AnnotationConfigApplicationContext annotationConfigApplicationContext
//                 = new AnnotationConfigApplicationContext(AnnotationConfig.class);
//         Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//         System.out.println(annotationConfigApplicationContext.getBean(Bean2.class).getBean1());
//     }
//
//     /**
//      * 经典容器，基于java配置类创建，用于web环境
//      */
//     static void testAnnotationConfigServletWebServerApplicationContext() {
//         AnnotationConfigServletWebServerApplicationContext servletWebServerApplicationContext = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
//
//     }
//
//     @Configuration
//     static class WebConfig {
//         /**
//          * 创建容器
//          */
//         @Bean
//         public ServletWebServerFactory servletWebServerFactory() {
//             return new TomcatServletWebServerFactory(8090);
//         }
//
//         /**
//          * 创建dispatcherServlet
//          */
//         @Bean
//         public DispatcherServlet dispatcherServlet() {
//             return new DispatcherServlet();
//         }
//
//         /**
//          * DispatcherServlet 关联 容器
//          */
//         @Bean
//         public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
//             return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
//         }
//
//         // 测试web类
//         @Bean("/hello")
//         public Controller controller1() {
//             return (request, response) -> {
//                 response.getWriter().println("HelloWorld !");
//                 return null;
//             };
//         }
//     }
//
//     @Configuration
//     static class AnnotationConfig {
//         @Bean
//         public Bean1 bean1() {
//             return new Bean1();
//         }
//
//         // todo 研究Bean1自动注入
//         @Bean
//         public Bean2 bean2(Bean1 bean1) {
//             Bean2 bean2 = new Bean2();
//             bean2.setBean1(bean1);
//             return bean2;
//         }
//     }
//
//     static class Bean1 {
//
//     }
//
//     static class Bean2 {
//         private Bean1 bean1;
//
//         public void setBean1(Bean1 bean1) {
//             this.bean1 = bean1;
//         }
//
//         public Bean1 getBean1() {
//             return bean1;
//         }
//     }
// }
