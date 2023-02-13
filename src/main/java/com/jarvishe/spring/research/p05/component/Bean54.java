package com.jarvishe.spring.research.p05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/9
 */
@Controller
public class Bean54 {
    private static final Logger logger = LoggerFactory.getLogger(Bean54.class);
    public Bean54() {
        logger.info(">>>>>>>>>>>>  我被执行构造方法了");
    }
}
