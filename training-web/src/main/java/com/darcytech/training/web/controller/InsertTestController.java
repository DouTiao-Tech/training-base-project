package com.darcytech.training.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darcytech.training.core.catalog.dao.Test1Dao;
import com.darcytech.training.core.catalog.model.Test1;
import com.google.common.base.Stopwatch;

@RestController
@RequestMapping("/test")
public class InsertTestController {

    @Autowired
    private Test1Dao testDao;

    private Random random = new Random();

    @RequestMapping("/batch")
    public long batch(@RequestParam(required = false, defaultValue = "1") int count,
                      @RequestParam(required = false, defaultValue = "1000") int batchSize) {
        Stopwatch started = Stopwatch.createStarted();
        for (int i = 0; i < count; i++) {
            testDao.save(generateRandom(batchSize));
            testDao.getEntityManager().clear();
        }
        return started.elapsed(TimeUnit.MILLISECONDS);
    }

    @RequestMapping("/concurrent")
    public long concurrent(@RequestParam(required = false, defaultValue = "1") int count,
                           @RequestParam(required = false, defaultValue = "1000") int batchSize,
                           @RequestParam(required = false, defaultValue = "100") int threads)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        try {
            Stopwatch started = Stopwatch.createStarted();
            for (int i = 0; i < count; i++) {
                List<Test1> random1 = generateRandom(batchSize);
                List<Callable<Object>> tasks = random1.stream().map(t -> (Callable<Object>) () -> {
                    testDao.save(t);
                    return null;
                }).collect(Collectors.toList());
                executor.invokeAll(tasks);
            }
            return started.elapsed(TimeUnit.MILLISECONDS);
        } finally {
            executor.shutdownNow();
        }
    }

    @RequestMapping("/always")
    public long always(@RequestParam(required = false, defaultValue = "1") int count,
                       @RequestParam(required = false, defaultValue = "1000") int batchSize,
                       @RequestParam(required = false, defaultValue = "100") int threads)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        try {
            Stopwatch started = Stopwatch.createStarted();
            CountDownLatch latch = new CountDownLatch(count * batchSize);
            for (int i = 0; i < threads; i++) {
                executor.execute(() -> {
                    while (latch.getCount() > 0) {
                        testDao.save(generateRandom(1));
                        latch.countDown();
                    }
                });
            }
            latch.await();
            return started.elapsed(TimeUnit.MILLISECONDS);
        } finally {
            executor.shutdownNow();
        }
    }

    private List<Test1> generateRandom(int size) {
        int time = (int) (System.currentTimeMillis() / 1000);
        List<Test1> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Test1 test = new Test1();
            test.setId(UUID.randomUUID().toString());
            test.setValue1(RandomStringUtils.randomAlphanumeric(random.nextInt(64)));
            test.setValue2(RandomStringUtils.randomAlphanumeric(random.nextInt(64)));
            test.setValue3(RandomStringUtils.randomAlphanumeric(random.nextInt(64)));
            test.setTime1(LocalDateTime.ofEpochSecond(random.nextInt(time), 0, ZoneOffset.ofHours(8)));
            test.setTime2(LocalDateTime.ofEpochSecond(random.nextInt(time), 0, ZoneOffset.ofHours(8)));
            test.setTime3(LocalDateTime.ofEpochSecond(random.nextInt(time), 0, ZoneOffset.ofHours(8)));
            test.setTime4(LocalDateTime.ofEpochSecond(random.nextInt(time), 0, ZoneOffset.ofHours(8)));
            list.add(test);
        }
        return list;
    }

}
