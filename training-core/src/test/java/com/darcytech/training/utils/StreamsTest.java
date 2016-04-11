package com.darcytech.training.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class StreamsTest {

    @Test
    public void sumBigDecimal() throws Exception {
        List<BigDecimal> values = Lists.newArrayList(new BigDecimal("10"), new BigDecimal("8.8"), new BigDecimal("12.12"));
        Optional<BigDecimal> sum = values.stream().reduce(BigDecimal::add);
        BigDecimal sum2 = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        Stream.of(sum.get(), sum2).forEach(System.out::println);
        Assert.assertEquals("30.92", sum.get().toString());
    }

    @Test
    public void streamSize() throws Exception {
        long count = Stream.of(10, 20, 30).filter(n->n>10).count();
        Assert.assertEquals(2L, count);
    }

}
