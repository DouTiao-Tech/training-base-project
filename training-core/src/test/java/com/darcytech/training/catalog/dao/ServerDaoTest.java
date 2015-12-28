package com.darcytech.training.catalog.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.catalog.model.Server;

public class ServerDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private ServerDao serverDao;

    @Before
    public void setUp() throws Exception {
        serverDao.saveAndFlush(new Server(10));
    }

    @Test
    public void findAllServer() throws Exception {
        Assert.assertEquals(1, serverDao.findAll().size());
    }

}
