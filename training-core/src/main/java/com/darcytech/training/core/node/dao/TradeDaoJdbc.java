package com.darcytech.training.core.node.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.darcytech.training.core.node.NodeReadOnlyTx;
import com.darcytech.training.core.node.NodeRepositoryConfig;

@NodeReadOnlyTx
public class TradeDaoJdbc {

    @Autowired
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    private JdbcTemplate jdbcTemplate;

}
