package com.darcytech.training.core.base;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.google.common.base.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseJpaModel<ID> {

    public abstract ID getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().isAssignableFrom(o.getClass()) && !o.getClass().isAssignableFrom(getClass())) {
            return false;
        }
        BaseJpaModel<?> baseModel = (BaseJpaModel<?>) o;
        return Objects.equal(getId(), baseModel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
