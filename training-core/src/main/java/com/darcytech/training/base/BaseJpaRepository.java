package com.darcytech.training.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.common.collect.Iterables;

public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    JdbcTemplate getJdbcTemplate();

    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

    EntityManager getEntityManager();

    default T findOne(Long userId, ID id) {
        T t = findOne(id);
        return userId.equals(getUserId(t)) ? t : null;
    }

    default List<T> findAllInBatch(Long userId, Iterable<ID> ids) {
        List<T> results = findAllInBatch(ids);
        return results.stream().filter(o -> getUserId(o).equals(userId)).collect(Collectors.toList());
    }

    default List<T> findAllInBatch(Iterable<ID> ids) {
        List<T> results = new ArrayList<>(Iterables.size(ids));
        Iterable<List<ID>> batches = Iterables.partition(ids, 50);
        for (List<ID> batch : batches) {
            results.addAll(findAll(batch));
        }
        return results;
    }

    default Specification<T> eq(SingularAttribute<T, ?> attr, Object value) {
        return value == null ? null : (root, query, cb) -> cb.equal(root.get(attr), value);
    }

    default <J> Specification<T> eq(SingularAttribute<T, J> joinAttr, SingularAttribute<J, ?> attr, Object value) {
        return value == null ? null : (root, query, cb) -> cb.equal(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    default Specification<T> lt(SingularAttribute<T, ? extends Number> attr, Number value) {
        return value == null ? null : (root, query, cb) -> cb.lt(root.get(attr), value);
    }

    default <J> Specification<T> lt(SingularAttribute<T, J> joinAttr,
                                    SingularAttribute<J, ? extends Number> attr, Number value) {
        return value == null ? null : (root, query, cb) -> cb.lt(root.join(joinAttr, JoinType.LEFT).get(attr), value);
    }

    default <Y extends Comparable<? super Y>> Specification<T> le(SingularAttribute<T, Y> attr, Y value) {
        return value == null ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    static Object getUserId(Object o) {
        try {
            return PropertyUtils.getProperty(o, "userId");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AssertionError("cannot get userId property", e);
        }
    }

}
