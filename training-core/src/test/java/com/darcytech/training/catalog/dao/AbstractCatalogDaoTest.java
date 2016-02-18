package com.darcytech.training.catalog.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.BaseDaoTest;
import com.darcytech.training.catalog.CatalogRepositoryConfig;

@Transactional(CatalogRepositoryConfig.TX_MANAGER_NAME)
public abstract class AbstractCatalogDaoTest extends BaseDaoTest {

    @PersistenceContext(unitName = CatalogRepositoryConfig.UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Autowired
    @Qualifier(CatalogRepositoryConfig.UNIT_NAME)
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

}