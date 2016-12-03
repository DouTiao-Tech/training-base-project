package com.darcytech.training.updater.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronTriggerScheduler {

    public static final String TRACE_LOG_CRON = "* * * 1";

    private volatile LocalDateTime lastExecuteTime = LocalDateTime.now();

    @Scheduled(cron = "${updater.scheduler.cronTrigger.cron:* * * * * *}")
    public void schedule() throws InterruptedException {
        throw new IllegalStateException("ssssss");

    }


}
