package com.darcytech.training.core.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.darcytech.training.core.base.BaseJpaRepository.exp;

@Repository
@Transactional(readOnly = true)
public class EnhancedJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final EntityManager entityManager;

    private final JpaEntityInformation<T, ?> entityInformation;

    public EnhancedJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(entityManager));
        this.entityInformation = entityInformation;
    }

    public JdbcTemplate getJdbcTemplate() {
        return (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional
    public int batchDeleteByIds(Iterable<ID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            return 0;
        }
        int size = 0;
        for (List<ID> partIds : Query2.partitionForQuery(ids)) {
            size += deleteByIds(partIds);
        }
        return size;
    }

    @Transactional
    public void update(T entity) {
        Session session = entityManager.unwrap(Session.class);
        session.update(entity);
    }

    @Transactional
    public void update(Iterable<T> entities) {
        Session session = entityManager.unwrap(Session.class);
        for (T e : entities) {
            session.update(e);
        }
    }

    private int deleteByIds(List<ID> ids) {
        if (entityInformation.hasCompositeId()) {
            throw new UnsupportedOperationException("composite id is unsupported");
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaDelete<T> delete = cb.createCriteriaDelete(getDomainClass());
        Root<T> from = delete.from(getDomainClass());

        Predicate predicate = from.get(entityInformation.getIdAttribute()).in(exp(cb, ids));
        return entityManager.createQuery(delete.where(predicate)).executeUpdate();
    }

    // --- 以下是 JPA 查询的 Join 写法

    private DataSource getDataSource(EntityManager entityManager) {
        SessionFactoryImpl sf = entityManager.getEntityManagerFactory().unwrap(SessionFactoryImpl.class);
        return ((DatasourceConnectionProviderImpl) sf.getServiceRegistry().getService(ConnectionProvider.class)).getDataSource();
    }

}
