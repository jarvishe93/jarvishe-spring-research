package com.jarvishe.spring.research.p04;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 类描述
 *
 * @author: jarvis
 * @date: 2023/2/7
 */
@ConfigurationProperties(prefix = "java")
public class Bean44 {
    private String home;
    private String version;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Bean44{" +
                "name='" + home + '\'' +
                ", age='" + version + '\'' +
                '}';
    }
}
