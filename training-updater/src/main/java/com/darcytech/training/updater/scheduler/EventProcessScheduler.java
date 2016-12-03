//package com.darcytech.training.updater.scheduler;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EventProcessScheduler {
//
//    private RedisTemplate<String, String> redisTemplate;
//
//    public void start() {
//        CustomizableThreadFactory th = new CustomizableThreadFactory("event-");
//        for(int i = 0; i < 100; i++) {
//            th.newThread(processor(i)).start();
//        }
//    }
//
//    private Runnable processor(int processorId) {
//        String sourceKey = "event:queue:waiting";
//        String destKey = "event:queue:processing:" + processorId;
//        return () -> {
//          while(true) {
//              String event = redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destKey, 10, TimeUnit.SECONDS);
//              if (event != null) {
//                  process(event);
//                  redisTemplate.boundListOps(destKey).leftPop();
//              }
//          }
//        };
//    }
//
//    private void process(String event) {
//    }
//
//}
