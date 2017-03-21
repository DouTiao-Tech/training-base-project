package com.darcytech.training.core.catalog.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    @Test
    public void batchDeleteByIds() throws Exception {
        itemOrderDao.deleteInBatchByIds(Arrays.asList(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    public void findByIidIn() {
        itemOrderDao.findByIidIn(new ArrayList<>());
        itemOrderDao.findByIidIn(Collections.singletonList(1L));
        itemOrderDao.findByIidIn(Arrays.asList(1L, 2L));
        itemOrderDao.findByIidIn(Arrays.asList(1L, 2L, 3L));
        itemOrderDao.findByIidIn(Arrays.asList(1L, 2L, 3L, 4L));

        Collection<Long> ids11 = Collections.nCopies(11, 10L);
        itemOrderDao.findByIidIn(ids11);

        Collection<Long> ids60 = Collections.nCopies(60, 10L);
        itemOrderDao.findByIidIn(ids60);

    }

}