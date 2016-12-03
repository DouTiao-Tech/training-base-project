package com.darcytech.training.core.catalog.dao;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.catalog.model.Customer;
import com.darcytech.training.core.catalog.model.CustomerId;

public interface CustomerDao extends BaseJpaRepository<Customer, CustomerId> {

//    Customer findByUserIdAndNick(Long userId, String nick);

}
