package com.yyn.demo;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
public class Application {

    public Application(@Qualifier("localRedisTemplate") RedisTemplate<String, String> localRedisTemplate,
            @Qualifier("redisTemplate") RedisTemplate<String, String> defaultRedisTemplate)
            throws InterruptedException {
        // 10s的有效时间
        localRedisTemplate.opsForValue().set("key", "value", 10000, TimeUnit.MILLISECONDS);


        defaultRedisTemplate.opsForValue().set("key", "value", 10000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
