package com.jarvishe.spring.research.p05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/9
 */
@Service
public class Bean53 {
    private static final Logger logger = LoggerFactory.getLogger(Bean53.class);
    public Bean53() {
        logger.info(">>>>>>>>>>>>  我被执行构造方法了");
    }
}
