package com.darcytech.training.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

}
