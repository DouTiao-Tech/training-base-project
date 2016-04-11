package com.darcytech.training.node.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.data.annotation.LastModifiedDate;

import com.darcytech.training.base.BaseJpaModel;

@Entity
public class ShopDailyVisit extends BaseJpaModel<ShopDailyId> {

    @EmbeddedId
    private ShopDailyId id;

    @LastModifiedDate
    private LocalDateTime lastModified;

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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
