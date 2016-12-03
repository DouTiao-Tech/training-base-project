package com.darcytech.training.core.catalog.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.catalog.model.Cart;
import com.darcytech.training.core.catalog.model.Customer;
import com.darcytech.training.core.catalog.model.ItemOrder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;

public class CartDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;

    private Cart cart;

    @Before
    public void setUp() throws Exception {

        Customer c = customerDao.save(new Customer(10L, "james", "james"));

        Cart cart = new Cart(c, 100);
        List<ItemOrder> orders = new ArrayList<>();
        orders.add(new ItemOrder(cart, 10L, 2));
        orders.add(new ItemOrder(cart, 20L, 1));
        cart.setOrders(orders);

        Customer c2 = customerDao.save(new Customer(11L, "darcy", "xx"));
        Cart cart2 = new Cart(c2, 200);
        List<ItemOrder> orders2 = new ArrayList<>();
        orders2.add(new ItemOrder(cart2, 10L, 2));
        orders2.add(new ItemOrder(cart2, 20L, 1));
        cart2.setOrders(orders2);
        List<Cart> carts = cartDao.save(Arrays.asList(cart, cart2));

        flushAndClear();

        this.cart = carts.get(0);
    }

    @Test
    public void findTop1000() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
//        Cart cart1 = cartDao.findOne(cart.getId());
        Cart cart = Iterables.getFirst(cartDao.findAll(), null);
        Assert.assertNotNull(cart);
//        Assert.assertSame(cart1, cart);
    }

    @Test
    public void findById_jdbcTemplate() throws Exception {
        Cart other = cartDao.findByIdWithJdbcTemplate(cart.getId());
        Assert.assertEquals(cart.getCreatedTime(), other.getCreatedTime());
    }

    @Test
    public void findCartIdByCreatedTime() throws Exception {
        cartDao.findCartIdByCreatedTime(cart.getCreatedTime());
    }

    @Test
    public void findIdByCreatedTime() {
        cartDao.findPaymentsByCreatedTime(cart.getCreatedTime());
    }

    @Test
    public void name() throws Exception {
        Map<String, Object> params = ImmutableMap.of("ids", Arrays.asList(1,2));
        customerDao.getNamedParameterJdbcTemplate().queryForList("select * from customer where name in (:ids)", params);
    }

}