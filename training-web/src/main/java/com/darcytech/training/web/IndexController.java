package com.darcytech.training.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/index1")
    @ResponseBody
    public String index1(Model model) {
        return "index1 is here";
    }

    @RequestMapping("/trades")
    public void test(Model model, Trade trade) {
        logger.info("" + trade.getLocalDay() + "," + trade.getLocalDateTime() + ", " + trade.getLocalTime());
    }

}
