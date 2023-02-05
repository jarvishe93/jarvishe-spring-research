package com.jarvishe.spring.research.p03.lifecycle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author: heguoliang
 * @date: 2023/2/5
 */
@Component
public class LifeCycleBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {
    Logger logger = LoggerFactory.getLogger(LifeCycleBean.class);

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor 销毁前");
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        if (StringUtils.equals("lifeCycleBean", bean.getClass().getName())) {
            logger.info("requiresDestruction 销毁前");
        }
        return true;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor 构造前，可替换bean");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor 构造后，是否需要注入其他属性");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor postProcessProperties相关");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor 初始化前，可替换bean，和类中PostConstruct冲突");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals("lifeCycleBean", beanName)) {
            logger.info("LifeCycleBeanPostProcessor 初始化后，可替换bean");
        }
        return null;
    }
}
