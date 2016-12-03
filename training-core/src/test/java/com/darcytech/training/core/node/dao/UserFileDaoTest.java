package com.darcytech.training.core.node.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.darcytech.training.core.node.model.UserFile;

public class UserFileDaoTest extends AbstractNodeDaoTest {

    @Autowired
    private UserFileDao userFileDao;

    private Integer userFileId;

    @Before
    public void setUp() throws Exception {
        UserFile userFile = new UserFile(1L, "file1.txt", "plain/text", new byte[]{1, 2, 3});
        userFile = userFileDao.save(userFile);
        flushAndClear();
        userFileId = userFile.getId();
    }

    @Test
    public void findOne_LazyContent() throws Exception {
        UserFile userFile = userFileDao.findOne(userFileId);
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, userFile.getContent());
    }

    @Test
    public void findById_LazyContent() throws Exception {
        UserFile userFile = userFileDao.findById(userFileId);
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, userFile.getContent());
    }

}