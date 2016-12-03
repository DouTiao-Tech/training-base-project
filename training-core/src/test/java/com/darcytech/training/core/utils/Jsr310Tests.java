package com.darcytech.training.core.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class Jsr310Tests {

    @Test
    public void plusOrMinus() throws Exception {
        LocalDateTime payTime = LocalDateTime.of(2015, 10, 10, 19, 0);
        Assert.assertEquals(LocalDateTime.of(2015, 10, 30, 19, 0), payTime.plusDays(20));
        Assert.assertEquals(LocalDateTime.of(2015, 10, 11, 5, 0), payTime.plusHours(10));
        Assert.assertEquals(LocalDateTime.of(2015, 9, 30, 19, 0), payTime.minusDays(10));
    }

    @Test
    public void parse() throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate day = LocalDate.parse("1900-01-01", dateFormatter);
        Assert.assertEquals(LocalDate.of(1900, 1, 1), day);
    }

    @Test
    public void format() throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        Assert.assertEquals("2011-11-11", dateFormatter.format(LocalDate.of(2011, 11, 11)));
    }

    @Test
    public void duration() throws Exception {
        LocalDateTime prev = LocalDateTime.of(2015, 1, 1, 0, 0);
        LocalDateTime next = LocalDateTime.of(2015, 1, 2, 10, 0);
        Duration between = Duration.between(prev, next);
        Assert.assertEquals(122400L, between.getSeconds());
        Assert.assertEquals(122400L, prev.until(next, ChronoUnit.SECONDS));
        LoggerFactory.getLogger(this.getClass()).info("11222222");
    }

    @Test
    public void localDateDuration() throws Exception {
        LocalDate today = LocalDate.of(2016, 4, 3);
        LocalDate daysLater = LocalDate.of(2016, 5, 7);
        long days = today.until(daysLater, ChronoUnit.DAYS);
        Assert.assertEquals(34, days);
    }
}
