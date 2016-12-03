package com.darcytech.training.core.node.dao;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.SimpleTrade;

public interface SimpleTradeDao extends BaseJpaRepository<SimpleTrade, Long> {

    SimpleTrade findByTid(long tid);

}
