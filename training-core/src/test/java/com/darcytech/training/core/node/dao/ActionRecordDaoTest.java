package com.darcytech.training.core.node.dao;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActionRecordDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private ActionRecordDao actionRecordDao;

    @Test
    public void findByJobId() throws Exception {
        actionRecordDao.findByJobId(1, "paid", "notPhone", LocalDate.now().atStartOfDay(), null);
    }

}