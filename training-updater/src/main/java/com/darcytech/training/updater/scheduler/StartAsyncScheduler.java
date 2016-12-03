package com.darcytech.training.updater.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

@Component
public class StartAsyncScheduler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger integer = new AtomicInteger();

    private volatile boolean started;

    public synchronized void startAsync() {
        if (!started) {
            started = true;
            CustomizableThreadFactory tf = new CustomizableThreadFactory("sleep");
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(tf);
            executor.scheduleWithFixedDelay(() -> {
                logger.info(integer.incrementAndGet() + " do something");
            }, 1, 1, TimeUnit.SECONDS);
        }
    }

}
