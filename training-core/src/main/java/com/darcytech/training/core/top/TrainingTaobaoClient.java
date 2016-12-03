package com.darcytech.training.core.top;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TrainingTaobaoClient {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Retryable(exclude = {NullPointerException.class})
    @Cacheable("trades")
    @Transactional
    public List<Long> findTrade(Long tid) {
        logger.info("findTrade called");
        if (tid % 2 == 0) {
            throw new NullPointerException();
        }
        if (tid % 3 == 0) {
            throw new IllegalStateException("aaaaa");
        }
        return Collections.singletonList(tid);
    }

}
