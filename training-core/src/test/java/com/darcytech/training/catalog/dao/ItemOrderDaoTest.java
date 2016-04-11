package com.darcytech.training.catalog.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemOrderDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private ItemOrderDao itemOrderDao;

    @Test
    public void findPaymentsLessThan() throws Exception {
        itemOrderDao.findPaymentsLessThan(10);
    }

}