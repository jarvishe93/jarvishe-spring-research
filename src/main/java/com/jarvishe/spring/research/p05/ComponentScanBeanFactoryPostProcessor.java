package com.jarvishe.spring.research.p05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * 自定义ComponentScan注解功能实现
 *
 * @author: jarvis
 * @date: 2023/2/14
 */
public class ComponentScanBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            if (!(configurableListableBeanFactory instanceof DefaultListableBeanFactory)) {
                return;
            }
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (Objects.isNull(componentScan)) {
                return;
            }
            // 获取注解信息
            for (String basePackage : componentScan.basePackages()) {
                System.out.println("basePackage = " + basePackage);
                String path = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                // 包名转换为path，获取类文件路径
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                // 利用readerFactory读取资源信息
                CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
                for (Resource resource : resources) {
                    MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    String className = classMetadata.getClassName();
                    System.out.println("类名: " + className);
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    boolean hasComponentAnnotation = annotationMetadata.hasAnnotation(Component.class.getName());
                    System.out.println("有没有@Component： " + hasComponentAnnotation);
                    boolean hasComponentMetaAnnotation = annotationMetadata.hasMetaAnnotation(Component.class.getName());
                    System.out.println("有没有@Component相关派生： " + hasComponentMetaAnnotation);
                    System.out.println();
                    // 讲扫描到的类，加入到spring管理
                    if (hasComponentAnnotation || hasComponentMetaAnnotation) {
                        // 通过beanDefinition注册
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
                        // 通过beanDefinition自动获取beanName
                        AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
                        String beanName = beanNameGenerator.generateBeanName(beanDefinition, beanFactory);
                        beanFactory.registerBeanDefinition(beanName, beanDefinition);
                    }
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
