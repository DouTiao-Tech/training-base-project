package com.darcytech.training.core.node.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.WangwangInfo;

@Transactional(readOnly = true)
public interface WangwangInfoDao extends BaseJpaRepository<WangwangInfo, Long> {

    List<WangwangInfo> findByName(String name);

}
