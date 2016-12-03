package com.darcytech.training.core.catalog.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.darcytech.training.core.base.BaseJpaModel;

@Entity
public class ItemOrder extends BaseJpaModel<Long> {

    @Id @GeneratedValue
    private Long id;

    private int payment;

    private int itemNum;

    private long iid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    public ItemOrder() {
    }

    public ItemOrder(Cart cart, long iid, int itemNum) {
        this.cart = cart;
        this.itemNum = itemNum;
        this.iid = iid;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
