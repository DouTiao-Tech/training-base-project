package com.darcytech.training.core.node.dao;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.node.model.ShopDailyVisit;

public class ShopDailyVisitDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private ShopDailyVisitDao shopDailyVisitDao;

    @Before
    public void setUp() throws Exception {
        ShopDailyVisit visit = new ShopDailyVisit(10L, LocalDate.of(2015, 10, 2), 10);
        shopDailyVisitDao.saveAndFlush(visit);
        visit.setUserCount(100);
        shopDailyVisitDao.saveAndFlush(visit);
        detach(visit);
        shopDailyVisitDao.saveAndFlush(visit);
    }

    @Test
    public void findByUserId() throws Exception {
//        Assert.assertEquals(1, countRowsInTable("shop_daily_visit"));
    }

    @Test
    public void countObjects() throws Exception {
        Assert.assertEquals(1, shopDailyVisitDao.countObjects());
    }

    @Test
    public void findByUserCount() throws Exception {
        shopDailyVisitDao.findByUserCount(10);

    }
}