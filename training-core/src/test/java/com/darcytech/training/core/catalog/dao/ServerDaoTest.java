package com.darcytech.training.core.catalog.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private ServerDao serverDao;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate.execute("insert into server(id, db_host, db_port, db_username, db_password)" +
                " values(1, '192.168.100.10', 3306, 'darcy', '123456')");
    }

    @Test
    public void findAllServer() throws Exception {
        Assert.assertEquals(1, serverDao.findAll().size());
    }

}
