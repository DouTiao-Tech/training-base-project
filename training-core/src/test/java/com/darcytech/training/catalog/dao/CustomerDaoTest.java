package com.darcytech.training.catalog.dao;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.catalog.model.Customer;

public class CustomerDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindById() throws Exception {
        Optional<Customer> customer = customerDao.findById(10L);

    }
}