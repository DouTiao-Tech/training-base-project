package com.darcytech.training.updater.scheduler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
@EnableConfigurationProperties
@EnableScheduling
public class SchedulerConfiguration {

    private Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Bean
    @ConfigurationProperties(prefix = "updater.scheduler")
    public SchedulerProperties schedulerProperties() {
        return new SchedulerProperties();
    }

    @Bean
    public ScheduledThreadPoolExecutor schedulerExecutor() {
        int poolSize = schedulerProperties().getSchedulerSize();
        return new ScheduledThreadPoolExecutor(poolSize, new CustomizableThreadFactory("schedule-"));
    }

    @Bean
    @Primary
    public ThreadPoolExecutor workerExecutor() {
        int poolSize = schedulerProperties().getWorkerSize();
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);
        CustomizableThreadFactory th = new CustomizableThreadFactory("worker-");
        return new ThreadPoolExecutor(poolSize, poolSize, 0L, MILLISECONDS, workQueue, th) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (t != null) {
                    logger.error("unexpected error!!", t);
                }
            }
        };
    }

}
