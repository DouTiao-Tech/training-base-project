package com.darcytech.training.core.node.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@Entity
public class SimpleTrade implements Persistable<Long> {

    @Id
    private Long tid;

    private LocalDateTime created;

    @ManyToOne
    private BuyerFollow buyerFollow;

    @Transient
    private boolean isNew;

    public SimpleTrade() {
    }

    public SimpleTrade(long tid, LocalDateTime created) {
        this.tid = tid;
        this.created = created;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public Long getId() {
        return tid;
    }

    public BuyerFollow getBuyerFollow() {
        return buyerFollow;
    }

    public void setBuyerFollow(BuyerFollow buyerFollow) {
        this.buyerFollow = buyerFollow;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SimpleTrade)) {
            return false;
        }
        SimpleTrade that = (SimpleTrade) o;
        return Objects.equals(that.getTid(), ((SimpleTrade) o).getTid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTid());
    }

}
