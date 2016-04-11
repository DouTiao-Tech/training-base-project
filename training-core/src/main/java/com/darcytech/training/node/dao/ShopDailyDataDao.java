package com.darcytech.training.node.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specifications;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.node.model.ShopDailyData;
import com.darcytech.training.node.model.ShopDailyData_;
import com.darcytech.training.node.model.ShopDailyId;


public interface ShopDailyDataDao extends BaseJpaRepository<ShopDailyData, ShopDailyId> {

    List<ShopDailyData> findByIdUserId(long userId);

    List<ShopDailyData> findByIdDay(LocalDate day);

    default List<ShopDailyData> findBy() {
        return findAll(Specifications.where(eq(ShopDailyData_.id, 1)));
    }

    default int count1() {
        return getJdbcTemplate().queryForObject("", Integer.class);
    }

}
