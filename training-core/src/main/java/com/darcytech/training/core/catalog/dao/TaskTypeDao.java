package com.darcytech.training.core.catalog.dao;

import java.util.List;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.catalog.model.TaskType;

public interface TaskTypeDao extends BaseJpaRepository<TaskType, Integer> {

    List<TaskType> findByUserId(Long userId);

}
