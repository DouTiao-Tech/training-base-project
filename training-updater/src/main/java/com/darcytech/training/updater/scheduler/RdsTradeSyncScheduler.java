package com.darcytech.training.updater.scheduler;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.darcytech.training.core.utils.StreamUtils.map;

@Component
public class RdsTradeSyncScheduler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Scheduled(fixedDelay = 1000)
    public void syncTrades() throws InterruptedException {
        int batchSize = 500;
        for (int lastSize = batchSize; lastSize >= batchSize;) {
//            List<Long> tidList = incrementFind(start, lastTid, batchSize);
            List<Long> tidList = Collections.singletonList(1L);
            List<Callable<Void>> tasks = map(tidList, tid -> ()->process(tid));
            executor.invokeAll(tasks);
            lastSize = tidList.size();
        }
    }

    private Void process(Long tid) {
        return null;
    }



}
