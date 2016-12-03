package com.darcytech.training.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darcytech.training.core.catalog.dao.CustomerDao;
import com.darcytech.training.core.catalog.model.Customer;
import com.darcytech.training.core.catalog.model.CustomerId;
import com.darcytech.training.core.catalog.service.CustomerService;
import com.darcytech.training.web.Account;
import com.darcytech.training.web.Trade;

@RestController
@RequestMapping("/rest")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private  CustomerDao customerDao;

    @RequestMapping("")
    public String all() {
        return "";
    }

    @RequestMapping("/index1")
    public String index1(Model model) {
        return "index1 is here";
    }

    @RequestMapping(value = "/trades", method = RequestMethod.POST)
    public Trade test(@RequestBody Trade trade) {
        logger.info("" + trade.getLocalDay() + "," + trade.getLocalDateTime() + ", " + trade.getLocalTime());
        return trade;
    }

    @RequestMapping("/accounts")
    public double account(Account account) {
        return account.getAmount().orElse(10.0);
    }

    @RequestMapping("/save")
    public void save (Model model) {
        customerDao.save(new Customer(10L, "James", "ssss"));
    }

    @RequestMapping("/findOne")
    public Customer findOne(Model model) {
        return customerService.findOne(new CustomerId(10L, "james"));
    }

    @RequestMapping("/findByUserIdAndNick")
    public Customer findByUserIdAndNick(Model model) {
        return customerService.findByUserIdAndNick(new CustomerId(10L, "james"));
    }

}
