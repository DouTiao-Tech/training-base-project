package com.darcytech.training.core.catalog.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.darcytech.training.core.base.BaseJpaModel;

@Entity
public class Customer extends BaseJpaModel<CustomerId> {

    @EmbeddedId
    private CustomerId customerId;

    private String name;

    private int deposit;

    @SuppressWarnings("unused")
    public Customer() {
        // used by hibernate or jackson
    }

    public Customer(Long userId, String nick, String name) {
        this.customerId = new CustomerId(userId, nick);
        this.name = name;
    }

    @Override
    public CustomerId getId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

}
