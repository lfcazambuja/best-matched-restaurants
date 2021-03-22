package com.restaurants.bestmatchedrestaurants.configuration;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            log.debug("Generating cache key: target={}, method={}, params={}", target, method, params);

            if (nonNull(params) && params.length > 0) {
                return Arrays.stream(params)
                    .map(String::valueOf)
                    .collect(joining(","));
            }

            return target.getClass()
                .getName()
                .concat("#")
                .concat(method.getName());
        };
    }

}
