package com.darcytech.training.core.node.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.ShopDailyData;
import com.darcytech.training.core.node.model.ShopDailyId;


public interface ShopDailyDataDao extends BaseJpaRepository<ShopDailyData, ShopDailyId> {

    @Transactional
    List<ShopDailyData> findByIdUserId(long userId);

    List<ShopDailyData> findByIdDay(LocalDate day);

    default List<ShopDailyData> findBy() {
        Specification<ShopDailyData> ab = null;
        return findAll(Specifications.where(ab));
    }

    default int count1() {
        return getJdbcTemplate().queryForObject("", Integer.class);
    }

}
