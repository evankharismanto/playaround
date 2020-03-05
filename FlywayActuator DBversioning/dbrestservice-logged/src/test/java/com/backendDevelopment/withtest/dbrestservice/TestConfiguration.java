package com.backendDevelopment.withtest.dbrestservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@Configuration
public class TestConfiguration {
    @Bean
    public TimeZone timeZone(){
        TimeZone defaultTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(defaultTimeZone);
        return defaultTimeZone;
    }
}