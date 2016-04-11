package com.darcytech.training.catalog.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.catalog.model.Customer;

public interface CustomerDao extends BaseJpaRepository<Customer, Long> {

    @Query(value = "select id from customer c", nativeQuery = true)
    List<Long> findIdsBy(String name);

    default List<Customer> findByName(String name) {
        return findAll(findIdsBy(name));
    }

    Optional<Customer> findById(Long id);

}
