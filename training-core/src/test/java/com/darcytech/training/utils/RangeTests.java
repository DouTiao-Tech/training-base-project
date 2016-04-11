package com.darcytech.training.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class RangeTests {

    @Test
    public void rangeClosed() throws Exception {
        LocalDate day = LocalDate.of(2016,4,4);
        LocalDate end = LocalDate.of(2016,4,10);
        int days = (int) day.until(end, ChronoUnit.DAYS);
        Object[] localDates = IntStream.rangeClosed(0, days).mapToObj(day::plusDays).toArray();
        System.out.println(Arrays.toString(localDates));
    }
}
