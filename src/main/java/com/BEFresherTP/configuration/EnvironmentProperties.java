package com.BEFresherTP.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentProperties {
    @Autowired
    private Environment env;

    @PostConstruct
    public void logApplicationProperties() {
        if (env == null) {
            System.out.println("env null");
        } else {
            System.out.println("port: " + env.getProperty("server.port"));
            System.out.println("datasource url: " + env.getProperty("spring.datasource.url"));
            System.out.println("DB username: " + env.getProperty("spring.datasource.username"));
            System.out.println("DB password: " + env.getProperty("spring.datasource.password"));
        }
    }
}
