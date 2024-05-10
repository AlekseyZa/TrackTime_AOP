package com.alekseyz.testtask.tracktime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public Executor saveDBTaskExecutor() {
        return Executors.newScheduledThreadPool(8);
    }

    @Bean
    public Executor trackTimeExecutor() {
        return Executors.newFixedThreadPool(4);
    }
}
