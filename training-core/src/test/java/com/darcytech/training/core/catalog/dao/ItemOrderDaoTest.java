package com.darcytech.training.core.catalog.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.node.dao.ShopDailyDataDao;

public class ItemOrderDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private ItemOrderDao itemOrderDao;

    @Autowired
    private ShopDailyDataDao shopDailyDataDao;

    @Test
    public void findPaymentsLessThan() throws Exception {
        itemOrderDao.findPaymentsLessThan(10);
        shopDailyDataDao.findByIdUserId(10L);
    }

}