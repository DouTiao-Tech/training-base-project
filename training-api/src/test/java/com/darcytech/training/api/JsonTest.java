package com.darcytech.training.api;

import java.time.LocalDate;

import org.junit.Assert;

public class JsonTest {

    @org.junit.Test
    public void testWriteValueAsString() throws Exception {
        Assert.assertEquals("[2011,10,10]", Json.writeValueAsString(LocalDate.of(2011, 10, 10)));
    }

}