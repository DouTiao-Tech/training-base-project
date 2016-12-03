package com.darcytech.training.core.node.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BuyerFollow {

    @Id
    private Integer id;

    private String buyerNick;

    @OneToMany(mappedBy = "buyerFollow", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SimpleTrade> simpleTrades;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Set<SimpleTrade> getSimpleTrades() {
        return simpleTrades;
    }

    public void setSimpleTrades(Set<SimpleTrade> simpleTrades) {
        this.simpleTrades = simpleTrades;
    }

    public void addSimpleTrade(SimpleTrade simpleTrade) {
        if (simpleTrades == null) {
            simpleTrades = new HashSet<>();
        }
        simpleTrades.add(simpleTrade);
        simpleTrade.setBuyerFollow(this);
    }

}
