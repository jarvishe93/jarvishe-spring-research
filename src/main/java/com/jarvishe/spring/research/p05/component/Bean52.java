package com.jarvishe.spring.research.p05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/9
 */
@Component
public class Bean52 {
    private static final Logger logger = LoggerFactory.getLogger(Bean52.class);
    public Bean52() {
        logger.info(">>>>>>>>>>>>  我被执行构造方法了");
    }
}
