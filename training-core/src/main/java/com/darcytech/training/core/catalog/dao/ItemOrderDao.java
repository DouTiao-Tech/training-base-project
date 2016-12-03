package com.darcytech.training.core.catalog.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.catalog.model.ItemOrder;

@Transactional
public interface ItemOrderDao extends BaseJpaRepository<ItemOrder, Long> {

    default List<Integer> findPaymentsLessThan(int payment) {
        return getJdbcTemplate().queryForList("select payment from item_order where payment < ?", Integer.class, payment);
    }

}
