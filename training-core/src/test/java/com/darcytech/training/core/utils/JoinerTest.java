package com.darcytech.training.core.utils;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.base.Joiner;

public class JoinerTest {

    Joiner joiner = Joiner.on(',').skipNulls();

    @Test
    public void guavaJoin() throws Exception {
        System.out.println(joiner.join(Arrays.asList("1", null, "2")));
    }

}
