package com.jarvishe.spring.research.p03.template;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试模板方法设计模式
 *
 * @author: jarvis
 * @date: 2023/2/6
 */
public class TestTemplateMethod {
    static Logger logger = LoggerFactory.getLogger(TestTemplateMethod.class);

    public static void main(String[] args) {
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        myBeanFactory.addBeanPostProcessor(object -> logger.info("添加@Autowired"));
        myBeanFactory.addBeanPostProcessor(object -> logger.info("添加@Resource"));
        Object bean = myBeanFactory.getBean();
        logger.info("getBean return = {}", bean);
    }

    static class MyBeanFactory {
        static List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

        public Object getBean() {
            Object object = new Object();
            step1();
            step2(object);
            step3();
            return object;
        }

        private void step1() {
            logger.info("step1，执行构造方法");
        }

        private void step2(Object object) {
            logger.info("step2，依赖注入");
            beanPostProcessors.forEach(beanPostProcessor -> beanPostProcessor.inject(object));
        }

        private void step3() {
            logger.info("step3，初始化");
        }

        public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
            beanPostProcessors.add(beanPostProcessor);
        }
    }

    interface BeanPostProcessor {
        void inject(Object object);
    }
}
