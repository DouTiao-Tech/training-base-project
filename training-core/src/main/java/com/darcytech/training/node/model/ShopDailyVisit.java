package com.darcytech.training.node.model;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.darcytech.training.base.BaseJpaModel;

@Entity
public class ShopDailyVisit extends BaseJpaModel<ShopDailyId> {

    @EmbeddedId
    private ShopDailyId id;

    private int count;

    public ShopDailyVisit() {
    }

    public ShopDailyVisit(long userId, LocalDate day, int count) {
        this.id = new ShopDailyId(userId, day);
        this.count = count;
    }

    @Override
    public ShopDailyId getId() {
        return id;
    }

    public void setId(ShopDailyId id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
