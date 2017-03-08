package com.darcytech.training.core.base;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class EnhancedJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private EntityManager entityManager;

    public EnhancedJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(entityManager));
    }

    public EnhancedJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public JdbcTemplate getJdbcTemplate() {
        return (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public static <T> Specification<T> eq(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.get(attr), value);
    }

    public static <T> Specification<T> in(SingularAttribute<T, ?> attr, Collection<?> values) {
        if (isNullOrEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> {
            Path<?> expression = root.get(attr);
            return cb.in(expression).in(values);
        };
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> lt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThan(root.get(attr), value);
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> lte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> gt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThan(root.get(attr), value);
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> gte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), value);
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> between(SingularAttribute<T, Y> attr, Y from, Y to) {
        if (from == null) {
            return lte(attr, to);
        }
        if (to == null) {
            return gte(attr, from);
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    // --- 以下是 JPA 查询的 Join 写法

    public static <T, J> Specification<T> eq(SingularAttribute<T, J> joinAttr, SingularAttribute<J, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    public static <T, J> Specification<T> lt(SingularAttribute<T, J> joinAttr,
                                      SingularAttribute<J, ? extends Number> attr, Number value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lt(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    public static <T, Y extends Comparable<? super Y>> Specification<T> le(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj instanceof String && ((String) obj).isEmpty();
    }

    private DataSource getDataSource(EntityManager entityManager) {
        SessionFactoryImpl sf = entityManager.getEntityManagerFactory().unwrap(SessionFactoryImpl.class);
        return ((DatasourceConnectionProviderImpl) sf.getServiceRegistry().getService(ConnectionProvider.class)).getDataSource();
    }

}
