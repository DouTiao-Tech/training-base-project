package com.darcytech.training.core.node.dao;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.UserFile;

public interface UserFileDao extends BaseJpaRepository<UserFile, Integer> {

    UserFile findById(Integer id);

}
