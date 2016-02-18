package com.darcytech.training.node.model;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.darcytech.training.base.BaseJpaModel;

@Entity
public class ShopDailyVisit extends BaseJpaModel<ShopDailyId> {

    @EmbeddedId
    private ShopDailyId id;

    private int userCount;

    public ShopDailyVisit() {
    }

    public ShopDailyVisit(long userId, LocalDate day, int userCount) {
        this.id = new ShopDailyId(userId, day);
        this.userCount = userCount;
    }

    @Override
    public ShopDailyId getId() {
        return id;
    }

    public void setId(ShopDailyId id) {
        this.id = id;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

}
