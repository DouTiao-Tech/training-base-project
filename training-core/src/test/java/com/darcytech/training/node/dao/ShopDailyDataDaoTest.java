package com.darcytech.training.node.dao;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.node.model.ShopDailyData;
import com.darcytech.training.node.model.ShopDailyId;

public class ShopDailyDataDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private ShopDailyDataDao shopDailyDataDao;

    @Before
    public void setUp() throws Exception {
        ShopDailyData daily = new ShopDailyData();
        daily.setId(new ShopDailyId(10L, LocalDate.of(2015, 11, 11)));
        daily.setSalesAmount(108);
        shopDailyDataDao.save(daily);
        flushAndClear();
    }

    @Test
    public void findByIdUserId() throws Exception {
        Assert.assertEquals(1, shopDailyDataDao.findByIdUserId(10L).size());
    }

    @Test
    public void findByIdDay() throws Exception {
        Assert.assertEquals(1, shopDailyDataDao.findByIdDay(LocalDate.of(2015, 11, 11)).size());
    }

}