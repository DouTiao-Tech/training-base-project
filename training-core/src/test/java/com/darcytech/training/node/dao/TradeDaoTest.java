package com.darcytech.training.node.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.darcytech.training.node.model.Trade;

public class TradeDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private TradeDao tradeDao;

    @Before
    public void setUp() throws Exception {
        tradeDao.save(new Trade(10L));
        flushAndClear();
    }

    @Test
    public void findByCreateTimeLe() throws Exception {
        LocalDateTime createTime = LocalDateTime.of(2016, 10, 10, 10, 0);
        Page<Trade> trades = tradeDao.findByCreateTime(createTime, new PageRequest(0, 10));
        Assert.assertEquals(1, trades.getContent().size());
        Assert.assertEquals(1, trades.getTotalElements());
    }

    @Test
    public void findByPaymentLeAndCreateTimeLe() throws Exception {
        LocalDateTime createTime = LocalDateTime.of(2015, 10, 10, 10, 0);
        List<Trade> trades = tradeDao.findByPaymentLeAndCreateTimeLe(10, createTime);
        Assert.assertEquals(0, trades.size());
    }

}
