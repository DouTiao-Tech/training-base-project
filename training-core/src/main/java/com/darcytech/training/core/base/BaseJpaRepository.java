package com.darcytech.training.core.base;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    JdbcTemplate getJdbcTemplate();

    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

    EntityManager getEntityManager();

    default Specification<T> eq(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.get(attr), value);
    }

    default Specification<T> in(SingularAttribute<T, ?> attr, Collection<?> values) {
        if (isNullOrEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> {
            Path<?> expression = root.get(attr);
            return cb.in(expression).in(values);
        };
    }

    default <Y extends Comparable<? super Y>> Specification<T> lt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThan(root.get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> lte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> gt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThan(root.get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> gte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> between(SingularAttribute<T, Y> attr, Y from, Y to) {
        if (from == null) {
            return lte(attr, to);
        }
        if (to == null) {
            return gte(attr, from);
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    // --- 以下是 JPA 查询的 Join 写法

    default <J> Specification<T> eq(SingularAttribute<T, J> joinAttr, SingularAttribute<J, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    default <J> Specification<T> lt(SingularAttribute<T, J> joinAttr,
                                    SingularAttribute<J, ? extends Number> attr, Number value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lt(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> le(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    default boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    default boolean isNullOrEmpty(Object obj) {
        return obj == null || obj instanceof String && ((String) obj).isEmpty();
    }

}
