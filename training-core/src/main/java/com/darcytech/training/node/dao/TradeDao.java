package com.darcytech.training.node.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.node.model.Trade;
import com.darcytech.training.node.model.Trade_;

public interface TradeDao extends BaseJpaRepository<Trade, Long> {

    default Page<Trade> findByCreateTime(LocalDateTime createTime, Pageable pageable) {
        return findAll(le(Trade_.createTime, createTime), pageable);
    }

    default List<Trade> findByPaymentLeAndCreateTimeLe(int payment, LocalDateTime createTime) {
        Specification<Trade> leCreateTime = le(Trade_.createTime, createTime);
        Specification<Trade> lePayment = le(Trade_.payment, payment);
        return findAll(Specifications.where(leCreateTime).and(lePayment));
    }

}
