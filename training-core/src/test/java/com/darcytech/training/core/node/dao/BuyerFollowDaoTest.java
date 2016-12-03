package com.darcytech.training.core.node.dao;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.node.model.BuyerFollow;
import com.darcytech.training.core.node.model.SimpleTrade;

public class BuyerFollowDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private BuyerFollowDao buyerFollowDao;

    @Before
    public void setUp() throws Exception {
        BuyerFollow follow = new BuyerFollow();
        follow.setBuyerNick("james");
        follow.setId(10);
        SimpleTrade trade1 = new SimpleTrade(1, LocalDateTime.of(2011, 1, 1, 1, 1));
        SimpleTrade trade2 = new SimpleTrade(2, LocalDateTime.of(2012, 1, 1, 2, 2));

        follow.addSimpleTrade(trade1);
        follow.addSimpleTrade(trade2);

        buyerFollowDao.save(follow);
        flushAndClear();
    }

    @Test
    public void deleteOneToMany() throws Exception {
        BuyerFollow follow1 = buyerFollowDao.findOne(1);
        System.out.println(follow1);
//        System.out.println(follow1.getSimpleTrades().size());
        List<BuyerFollow> follows = buyerFollowDao.findByBuyerNick("james");
        BuyerFollow follow = follows.get(0);
        for(Iterator<SimpleTrade> it = follow.getSimpleTrades().iterator(); it.hasNext();) {
            SimpleTrade st = it.next();
            if (st.getTid() == 1) {
                it.remove();
            }
        }
        buyerFollowDao.saveAndFlush(follow);
        flushAndClear();
    }

}