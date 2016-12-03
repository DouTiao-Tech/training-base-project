package com.darcytech.training.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.darcytech.training.core.catalog.dao.CartDao;
import com.darcytech.training.core.catalog.model.Cart;
import com.darcytech.training.core.catalog.model.User;
import com.darcytech.training.core.top.TrainingTaobaoClient;
import com.darcytech.training.core.utils.ThreadUtils;

@Controller
@RequestMapping("/h6")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrainingTaobaoClient trainingTaobaoClient;

    @Autowired
    private CartDao cartDao;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @RequestMapping(value = "/abc.html", produces = "text/html")
    public void abc() {
        System.out.println("1");
    }

    @RequestMapping("/abc.html")
    public
    @ResponseBody
    String abc2(Model model) {
        System.out.println("2");
        return "ok";
    }

    @RequestMapping("/test")
    public void test(User user) {
        System.out.println(user.getUsername());
    }

    @RequestMapping("/retry")
    public void findSomething(Long tid) {
        trainingTaobaoClient.findTrade(tid);
    }

    @RequestMapping("/wait")
    public void waitAllDone() {
        List<Future<?>> futureList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            int j = i;
            futureList.add(executorService.submit(()->process(j)));
        }
        try {
            ThreadUtils.waitAllDone(futureList);
        } catch (ExecutionException e) {
            logger.error("ssss", e);
//            e.printStackTrace();
        }
    }

    private void process(int j) {
        cartDao.save(new Cart());
    }

}
