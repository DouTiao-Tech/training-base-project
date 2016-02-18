package com.darcytech.training.catalog.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.catalog.model.Server;

public class ServerDaoJdbcTest extends AbstractCatalogDaoTest {

    @Autowired
    private ServerDaoJdbc serverDaoJdbc;

    @Autowired
    private ServerDao serverDao;

    @Before
    public void setUp() throws Exception {
        Server server = new Server(10);
        server.setDbHost("192.168.100.10");
        server.setDbName("training");
        server.setDbUsername("darcy");
        server.setDbPassword("123456");
        serverDao.saveAndFlush(server);
    }

    @Test
    public void findAllServer() throws Exception {
        Assert.assertEquals(1, serverDaoJdbc.findAll().size());
    }

}
