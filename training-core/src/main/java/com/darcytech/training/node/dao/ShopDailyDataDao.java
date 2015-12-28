package com.darcytech.training.node.dao;

import java.time.LocalDate;
import java.util.List;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.node.model.ShopDailyData;
import com.darcytech.training.node.model.ShopDailyId;

public interface ShopDailyDataDao extends BaseJpaRepository<ShopDailyData, ShopDailyId> {

    List<ShopDailyData> findByIdUserId(long userId);

    List<ShopDailyData> findByIdDay(LocalDate day);

}
