package com.darcytech.training.utils;

import org.junit.Test;

import com.google.common.base.Joiner;

public class JoinerTest {

    @Test
    public void guavaJoin() throws Exception {
        Joiner.on(',').skipNulls();
    }

}
