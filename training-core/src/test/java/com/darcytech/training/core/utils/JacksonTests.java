package com.darcytech.training.core.utils;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTests {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testStreamJackson() throws Exception {
        System.out.println(objectMapper.writeValueAsString(Stream.of(1,2,3)));
    }

    @Test
    public void cyclicSerialize() throws Exception {
        CyclicBean3 bean3 = new CyclicBean3("3");
        CyclicBean1 bean = new CyclicBean1("1", new CyclicBean2("2", bean3));
        System.out.println(objectMapper.writeValueAsString(bean));
    }

}
