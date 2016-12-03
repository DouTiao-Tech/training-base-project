package com.darcytech.training.core.node.dao;

import java.util.List;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.NodeReadOnlyTx;
import com.darcytech.training.core.node.model.ShopDailyId;
import com.darcytech.training.core.node.model.ShopDailyVisit;

@NodeReadOnlyTx
public interface ShopDailyVisitDao extends BaseJpaRepository<ShopDailyVisit, ShopDailyId> {

    default int countObjects() {
        return getJdbcTemplate().queryForObject("select count(1) from shop_daily_visit", Integer.class);
    }

    List<ShopDailyVisit> findByUserCount(int userCount);

}
