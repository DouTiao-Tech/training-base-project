package com.darcytech.training.catalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.darcytech.training.base.BaseJpaModel;

@Entity
public class Customer extends BaseJpaModel<Long> {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int deposit;

    Customer() {
        // used by hibernate
    }

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
