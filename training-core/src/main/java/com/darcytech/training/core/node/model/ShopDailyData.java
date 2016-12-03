package com.darcytech.training.core.node.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.darcytech.training.core.base.BaseJpaModel;

@Entity
public class ShopDailyData extends BaseJpaModel<ShopDailyId> {

    @EmbeddedId
    private ShopDailyId id;

    private long salesAmount;

    @Override
    public ShopDailyId getId() {
        return id;
    }

    public void setId(ShopDailyId id) {
        this.id = id;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(long salesAmount) {
        this.salesAmount = salesAmount;
    }

}
