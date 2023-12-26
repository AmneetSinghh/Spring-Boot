package com.example.cacheservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@EnableCaching
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    String redisHost;

    @Value("${spring.redis.port}")
    int redisPort;

    @Value("${spring.redis.username}")
    String redisUserName;


    @Bean
    public LettuceConnectionFactory connectionFactory() {
        log.info("Started redis connection configuration");
        LettuceConnectionFactory lettuceConnectionFactory;
        log.info("Started standalone configuration for redis");
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setUsername(redisUserName);
        standaloneConfiguration.setPassword(RedisPassword.none());
        standaloneConfiguration.setHostName(redisHost);
        standaloneConfiguration.setPort(redisPort);
        lettuceConnectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
        return lettuceConnectionFactory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        log.info("Started preparing RedisTemplate.");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        log.info("Completed preparing RedisTemplate :" + template);
        return template;
    }

}