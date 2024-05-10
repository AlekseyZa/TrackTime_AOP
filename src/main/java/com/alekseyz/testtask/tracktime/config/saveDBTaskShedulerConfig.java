package com.alekseyz.testtask.tracktime.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class saveDBTaskShedulerConfig implements SchedulingConfigurer {

    private final Executor saveDBTaskExecutor;

    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(saveDBTaskExecutor);
    }
}
