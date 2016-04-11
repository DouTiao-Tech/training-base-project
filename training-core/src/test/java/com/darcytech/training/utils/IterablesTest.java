package com.darcytech.training.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class IterablesTest {

    @Test
    public void fill() throws Exception {
        List<Integer> values = Lists.newArrayList(1,2,3);
        Integer[] array = values.toArray(new Integer[10]);
        Arrays.fill(array, values.size(), 10, Iterables.getLast(values));
        List<Integer> list = Lists.newArrayListWithCapacity(10);
        Collections.fill(list, Iterables.getLast(values));
        Iterable<Integer> concat = Iterables.concat(values, list);

    }
}
