package com.darcytech.training.core.catalog.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;

import org.springframework.data.annotation.CreatedDate;

import com.darcytech.training.core.base.BaseJpaModel;
import com.google.common.collect.Iterables;

@Entity
public class Cart extends BaseJpaModel<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    private Customer customer;

    @CreatedDate
    private LocalDateTime createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<ItemOrder> orders;

    public Cart() {
    }

    public Cart(Customer customer, int payment) {
        this.customer = customer;
    }

    @PostLoad
    public void onLoad() {
        System.out.printf("load");
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public List<ItemOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ItemOrder> orders) {
        this.orders = orders;
    }

    public static List<Customer> extractCustomers(List<Cart> carts) {
        return carts.stream().map(Cart::getCustomer).collect(Collectors.toList());
    }

    public static Iterable<ItemOrder> extractOrders(Iterable<Cart> carts) {
        return Iterables.concat(Iterables.transform(carts, Cart::getOrders));
    }

}
