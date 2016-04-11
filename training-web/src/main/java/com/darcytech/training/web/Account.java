package com.darcytech.training.web;

import java.util.OptionalDouble;

public class Account {

    private String accountId;

    private Double amount = 4.0;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public OptionalDouble getAmount() {
        return amount == null ? OptionalDouble.empty() : OptionalDouble.of(amount);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
