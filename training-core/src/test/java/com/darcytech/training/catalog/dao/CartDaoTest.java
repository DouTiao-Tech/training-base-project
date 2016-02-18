package com.darcytech.training.catalog.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.catalog.model.Cart;
import com.darcytech.training.catalog.model.Customer;
import com.darcytech.training.catalog.model.ItemOrder;
import com.google.common.collect.Iterables;

public class CartDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {

        Customer c = customerDao.save(new Customer("james"));

        Cart cart = new Cart(c, 100);
        List<ItemOrder> orders = new ArrayList<>();
        orders.add(new ItemOrder(cart, 10L, 2));
        orders.add(new ItemOrder(cart, 20L, 1));
        cart.setOrders(orders);

        Customer c2 = customerDao.save(new Customer("xx"));
        Cart cart2 = new Cart(c2, 200);
        List<ItemOrder> orders2 = new ArrayList<>();
        orders2.add(new ItemOrder(cart2, 10L, 2));
        orders2.add(new ItemOrder(cart2, 20L, 1));
        cart2.setOrders(orders2);
        cartDao.save(Arrays.asList(cart, cart2));

        flushAndClear();
    }

    @Test
    public void findAll() throws Exception {
        Cart cart = Iterables.getFirst(cartDao.findAll(), null);
        Assert.assertNotNull(cart);
        Customer customer = cart.getCustomer();
        System.out.println(customer.getClass());
        System.out.println(customer.getName());

        List<ItemOrder> orders = cart.getOrders();
        System.out.println(orders.size());
    }

}