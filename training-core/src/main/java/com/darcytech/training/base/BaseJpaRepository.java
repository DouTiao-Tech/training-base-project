package com.darcytech.training.base;

import java.io.Serializable;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

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

}
