package com.jarvishe.spring.research.p05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 常见bean工厂后处理器
 *
 * @author: jarvis
 * @date: 2023/2/9
 */
public class P05Application {
    private static final Logger logger = LoggerFactory.getLogger(P05Application.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 服务 @ComponentScan @Bean @ImportResource @Import()
        // context.addBeanFactoryPostProcessor(new ConfigurationClassPostProcessor());
        // 服务 @MapperScan
        // MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // mapperScannerConfigurer.setBasePackage("com.jarvishe.spring.research.p05.mapper");
        // context.addBeanFactoryPostProcessor(mapperScannerConfigurer);

        // 服务 @ComponentScan @Bean @ImportResource @Import()
        // context.registerBean(ConfigurationClassPostProcessor.class);
        // // 服务 @MapperScan
        // context.registerBean(MapperScannerConfigurer.class,
        //         bd -> bd.getPropertyValues().add("basePackage", "com.jarvishe.spring.research.p05.mapper"));
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(ComponentScanBeanFactoryPostProcessor.class).getBeanDefinition();
        // 通过beanDefinition自动获取beanName
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
        String beanName = beanNameGenerator.generateBeanName(beanDefinition, beanFactory);
        context.registerBean(beanName, ComponentScanBeanFactoryPostProcessor.class);

        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            logger.info(">>>>>>>>>>>> beanDefinitionName = {}", beanDefinitionName);
        }
        context.close();
    }
}
