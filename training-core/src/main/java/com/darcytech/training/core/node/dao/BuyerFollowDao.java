package com.darcytech.training.core.node.dao;

import java.util.List;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.BuyerFollow;

public interface BuyerFollowDao extends BaseJpaRepository<BuyerFollow, Integer> {

    List<BuyerFollow> findByBuyerNick(String buyerNick);

}
