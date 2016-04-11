package com.darcytech.training.catalog.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.catalog.model.Cart;

public interface CartDao extends BaseJpaRepository<Cart, Long> {

    List<Cart> findByCreatedTime(LocalDateTime createdTime);

    @Query(value = "select id from cart where created_time=?", nativeQuery = true)
    List<Long> findIdByCreatedTime(LocalDateTime createdTime);

    default List<Integer> findPaymentsByCreatedTime(LocalDateTime createdTime) {
        Object[] args = {Timestamp.valueOf(createdTime)};
        return getJdbcTemplate().queryForList("select payment from cart where created_time = ?", args, Integer.class);
    }

}
