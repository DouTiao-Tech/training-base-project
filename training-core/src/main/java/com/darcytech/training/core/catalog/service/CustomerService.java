package com.darcytech.training.core.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.catalog.dao.CustomerDao;
import com.darcytech.training.core.catalog.model.Customer;
import com.darcytech.training.core.catalog.model.CustomerId;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public Customer findOne(CustomerId id) {
        return customerDao.findOne(id);
    }

    public Customer findByUserIdAndNick(CustomerId id) {
        return null;
    }

}
