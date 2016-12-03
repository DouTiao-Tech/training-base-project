package com.darcytech.training.updater.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RdsRefundScheduler {

    @Scheduled(cron = "* * 1 10 11 *")
    public void syncRefunds() {
    }

}
