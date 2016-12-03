package com.darcytech.training.core.catalog.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.catalog.model.Customer;
import com.darcytech.training.core.catalog.model.CustomerId;

public class CustomerDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        customerDao.saveAndFlush(new Customer(10L, "james", "James Jin"));
        flushAndClear();
    }


    @Test
    public void testFindById() throws Exception {
        Customer customer = customerDao.findOne(new CustomerId(10L, "james"));

    }

}