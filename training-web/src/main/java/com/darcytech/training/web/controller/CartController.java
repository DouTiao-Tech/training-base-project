package com.darcytech.training.web.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darcytech.training.core.catalog.dao.CartDao;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @RequestMapping("/search/count")
    public int count() {
        return cartDao.countItems();
    }

    @RequestMapping("/search/bigCount")
    public BigInteger bigCount() {
        return cartDao.bigCountItems();
    }

}
