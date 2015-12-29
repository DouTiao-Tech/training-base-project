package com.darcytech.training.node.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.BaseDaoTest;
import com.darcytech.training.node.NodeRepositoryConfig;

@Transactional(NodeRepositoryConfig.TX_MANAGER_NAME)
public abstract class AbstractNodeDaoTest extends BaseDaoTest {

    @PersistenceContext(unitName = NodeRepositoryConfig.UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Autowired
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

}
