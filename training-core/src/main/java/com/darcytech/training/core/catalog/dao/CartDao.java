package com.darcytech.training.core.catalog.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.catalog.model.Cart;
import com.google.common.collect.Iterables;

@Transactional(readOnly = true)
public interface CartDao extends BaseJpaRepository<Cart, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Cart> findAndLockByCreatedTime(LocalDateTime createdTime);

    @Query(value = "select id, created_time from cart where created_time=?", nativeQuery = true)
    Object[] findIdByCreatedTime(Timestamp timestamp);

    List<Cart> queryLockByCreatedTime(LocalDateTime createdTime);

    @Query(value = "select id from Cart where createdTime=?1")
    List<Long> findCartIdByCreatedTime(LocalDateTime createdTime);

    default List<Integer> findPaymentsByCreatedTime(LocalDateTime createdTime) {
        Object[] args = {Timestamp.valueOf(createdTime)};
        return getJdbcTemplate().queryForList("select payment from cart where created_time = ?", args, Integer.class);
    }

    default int countItems() {
        return getJdbcTemplate().queryForObject("select count(1) from cart", Integer.class);
    }

    default BigInteger bigCountItems() {
        return getJdbcTemplate().queryForObject("select count(1) from cart", BigInteger.class);
    }

    default Cart findByIdWithJdbcTemplate(Long id) {
        BeanPropertyRowMapper<Cart> rowMapper = new BeanPropertyRowMapper<>(Cart.class);
        return Iterables.getFirst(getJdbcTemplate().query("select * from cart where id=?", rowMapper, id), null);
    }

    default void findById(int id) {
        getJdbcTemplate().execute("select * from cartaaa");
    }

}
