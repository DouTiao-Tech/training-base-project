package com.darcytech.training.node.dao;

import com.darcytech.training.base.BaseJpaRepository;
import com.darcytech.training.node.model.UserFile;

public interface UserFileDao extends BaseJpaRepository<UserFile, Integer> {

    UserFile findById(Integer id);

}
