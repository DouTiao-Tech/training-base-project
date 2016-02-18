package com.darcytech.training;

import javax.persistence.EntityManager;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@SpringApplicationConfiguration(classes = DaoTestConfig.class)
public abstract class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    public void detach(Object... objects) {
        for (Object o : objects) {
            entityManager.detach(o);
        }
    }

}
