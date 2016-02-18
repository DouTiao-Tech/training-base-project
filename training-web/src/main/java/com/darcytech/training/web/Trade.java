package com.darcytech.training.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Trade {

    private LocalDate localDay;

    private LocalTime localTime;

    private LocalDateTime localDateTime;

    public LocalDate getLocalDay() {
        return localDay;
    }

    public void setLocalDay(LocalDate localDay) {
        this.localDay = localDay;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

}
