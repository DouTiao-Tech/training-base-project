package com.darcytech.training.updater.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    @Scheduled(fixedDelay = 60000)
    public void generateJobsFixedDelay() {
    }

    public void generateJobs() {
    }

}
