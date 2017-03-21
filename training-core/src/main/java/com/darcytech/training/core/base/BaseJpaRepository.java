package com.darcytech.training.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;

public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Transactional(readOnly = true)
    default List<T> batchFindAll(Iterable<ID> ids) {
        List<T> result = new ArrayList<T>(Iterables.size(ids));
        for (List<ID> partIds : Query2.partitionForQuery(ids)) {
            result.addAll(findAll(partIds));
        }
        return result;
    }

    int batchDeleteByIds(Iterable<ID> ids);

    <S extends T> void update(S entity);

    <S extends T> void update(Iterable<S> entities);

    JdbcTemplate getJdbcTemplate();

    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

    EntityManager getEntityManager();

    static <T> Specification<T> eq(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.get(attr), exp(cb, value));
    }

    static <T> Specification<T> in(SingularAttribute<T, ?> attr, Collection<?> values) {
        if (isNullOrEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> cb.in(root.get(attr)).in(exp(cb, values));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> lt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThan(root.get(attr), exp(cb, value));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> lte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), exp(cb, value));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> gt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThan(root.get(attr), exp(cb, value));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> gte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), exp(cb, value));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> between(SingularAttribute<T, Y> attr, Y from, Y to) {
        if (from == null) {
            return lte(attr, to);
        }
        if (to == null) {
            return gte(attr, from);
        }
        return (root, query, cb) -> cb.between(root.get(attr), exp(cb, from), exp(cb, to));
    }

    static <T, J> Specification<T> eq(SingularAttribute<T, J> joinAttr, SingularAttribute<J, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.join(joinAttr, JoinType.LEFT).get(attr), exp(cb, value));
    }

    static <T, J> Specification<T> lt(SingularAttribute<T, J> joinAttr,
                                      SingularAttribute<J, ? extends Number> attr, Number value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lt(root.join(joinAttr, JoinType.LEFT).get(attr), exp(cb, value));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> le(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), exp(cb, value));
    }

    static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj instanceof String && ((String) obj).isEmpty();
    }

    static <L> Expression<L> exp(CriteriaBuilder cb, L literal) {
        return new LiteralExpression2<>((CriteriaBuilderImpl) cb, literal);
    }

    static <L> Expression[] exp(CriteriaBuilder cb, Collection<L> values) {
        Expression[] result = new Expression[Query2.sizeAfterPadForQuery(values.size())];
        int i = 0;
        for (L value : Query2.padForQuery(values)) {
            result[i++] = exp(cb, value);
        }
        return result;
    }

}
