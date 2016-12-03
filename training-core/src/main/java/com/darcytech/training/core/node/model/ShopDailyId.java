package com.darcytech.training.core.node.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;

import com.google.common.base.Objects;

@Embeddable
public class ShopDailyId implements Serializable {

    private Long userId;

    private LocalDate day;

    private ShopDailyId() {
        // used by hibernate
    }

    public ShopDailyId(long userId, LocalDate day) {
        this.userId = userId;
        this.day = day;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShopDailyId shopDailyId = (ShopDailyId) o;
        return Objects.equal(getUserId(), shopDailyId.getUserId()) &&
                Objects.equal(getDay(), shopDailyId.getDay());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserId(), getDay());
    }

}
