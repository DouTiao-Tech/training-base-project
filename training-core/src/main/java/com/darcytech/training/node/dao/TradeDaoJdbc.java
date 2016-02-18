package com.darcytech.training.node.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.darcytech.training.node.NodeReadOnlyTx;
import com.darcytech.training.node.NodeRepositoryConfig;

@NodeReadOnlyTx
public class TradeDaoJdbc {

    @Autowired
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    private JdbcTemplate jdbcTemplate;

}
