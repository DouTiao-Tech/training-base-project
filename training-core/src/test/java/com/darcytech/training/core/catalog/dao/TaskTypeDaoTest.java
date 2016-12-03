package com.darcytech.training.core.catalog.dao;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.catalog.model.TaskStatus;
import com.darcytech.training.core.catalog.model.TaskType;

public class TaskTypeDaoTest extends AbstractCatalogDaoTest {

    @Autowired
    private TaskTypeDao taskTypeDao;

    @Before
    public void setUp() throws Exception {
        TaskType refundType = new TaskType(10L, "退款");
        TaskStatus status1 = new TaskStatus("已退货");
        TaskStatus status2 = new TaskStatus("已退货");
        refundType.addTaskStatus(status1);
        refundType.addTaskStatus(status2);
        taskTypeDao.save(refundType);
        flushAndClear();
    }

    @Test
    public void findByUserId() throws Exception {
        List<TaskType> types = taskTypeDao.findByUserId(10L);
        Optional<TaskType> taskType = types.stream().findFirst();
        if (taskType.isPresent()) {
            TaskType taskType1 = taskType.get();
            TaskStatus oldStatus = taskType1.getTaskStatuses().get(0);
            taskType1.clearTaskStatuses();
            taskType1.addTaskStatus(new TaskStatus("已完成"));
            taskType1.addTaskStatus(oldStatus);
            taskTypeDao.saveAndFlush(taskType1);
        }
        jdbcTemplate.queryForList("select * from task_status");
    }

}