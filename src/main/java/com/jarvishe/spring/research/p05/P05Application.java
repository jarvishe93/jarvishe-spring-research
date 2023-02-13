package com.jarvishe.spring.research.p05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 常见bean工厂后处理器
 *
 * @author: jarvis
 * @date: 2023/2/9
 */
public class P05Application {
    private static final Logger logger = LoggerFactory.getLogger(P05Application.class);

    public static void main(String[] args) throws Exception {
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

        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (!Objects.isNull(componentScan)) {
            // 获取注解信息
            for (String basePackage : componentScan.basePackages()) {
                System.out.println("basePackage = " + basePackage);
                String path = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                // 包名转换为path，获取类文件路径
                Resource[] resources = context.getResources(path);
                // 利用readerFactory读取资源信息
                CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
                for (Resource resource : resources) {
                    MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    String className = classMetadata.getClassName();
                    System.out.println("类名: " + className);
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    System.out.println("有没有@Component： " + annotationMetadata.hasAnnotation(Component.class.getName()));
                    System.out.println("有没有@Component相关派生： " + annotationMetadata.hasMetaAnnotation(Component.class.getName()));
                    System.out.println();
                }
            }
        }

        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            logger.info(">>>>>>>>>>>> beanDefinitionName = {}", beanDefinitionName);
        }
        context.close();
    }
}
