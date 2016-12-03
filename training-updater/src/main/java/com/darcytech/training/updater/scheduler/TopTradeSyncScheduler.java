package com.darcytech.training.updater.scheduler;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.darcytech.training.core.utils.RunningFutures;

@Component
public class TopTradeSyncScheduler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RunningFutures<Long> runningUsers = new RunningFutures<>();

    @Autowired
    private ExecutorService worker;

    @Scheduled(fixedDelay = 1000)
    public void syncTrades() {
        List<Long> validUserIds = findValidUserIds();
        for(Long userId : runningUsers.cleanAndRejectRunning(validUserIds)) {
            Future<?> future = worker.submit(() -> syncUserTrade(userId));
            runningUsers.register(userId, future);
        }
    }

    private void syncUserTrade(Long userId) {
        logger.info("find sync item for user " + userId);
        logger.info("find user session for user " + userId);
        logger.info(" sync one step " + userId);
    }

    private List<Long> findValidUserIds() {
        return Collections.emptyList();
    }

}
