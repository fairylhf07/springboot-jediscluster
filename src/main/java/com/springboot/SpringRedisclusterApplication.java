package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringRedisclusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisclusterApplication.class, args);
    }

}
