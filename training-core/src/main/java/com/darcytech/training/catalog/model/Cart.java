package com.darcytech.training.catalog.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import com.darcytech.training.base.BaseJpaModel;
import com.google.common.collect.Iterables;

@Entity
public class Cart extends BaseJpaModel<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    private Customer customer;

    @CreatedDate
    private LocalDateTime createdTime;

    private int payment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<ItemOrder> orders;

    public Cart() {
    }

    public Cart(Customer customer, int payment) {
        this.customer = customer;
        this.payment = payment;
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

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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
