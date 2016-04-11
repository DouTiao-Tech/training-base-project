package com.darcytech.training.utils;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalTest {

    @Test
    public void divide() throws Exception {
        System.out.println(new BigDecimal(4).divide(new BigDecimal(3), 4, BigDecimal.ROUND_HALF_UP));

    }
}
