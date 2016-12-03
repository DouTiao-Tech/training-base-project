package com.darcytech.training.core.node.dao;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.node.model.SimpleTrade;

public class SimpleTradeDaoTest extends AbstractNodeDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpleTradeDao simpleTradeDao;

    private SimpleTrade simpleTrade;

    @Before
    public void setUp() throws Exception {
        //        simpleTrade = new SimpleTrade(1L, LocalDateTime.now());
        //        simpleTradeDao.save(simpleTrade);
        //        flushAndClear();
    }

    @Test
    public void save() throws Exception {
        SimpleTrade st = simpleTradeDao.findByTid(1L);

        System.out.printf("##################");

        if (st == null) {
            st = new SimpleTrade(1L, LocalDateTime.now());
            st.setIsNew(true);
        }

        //        EntityManager em = simpleTradeDao.getEntityManager();
        //        em.persist(st);
        //        em.flush();

        simpleTradeDao.saveAndFlush(st);

    }

}