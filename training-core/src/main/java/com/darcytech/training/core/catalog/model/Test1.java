package com.darcytech.training.core.catalog.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "test")
public class Test1 implements Persistable<String> {

    @Id
    private String id;

    private String value1;

    private String value2;

    private String value3;

    private LocalDateTime time1;

    private LocalDateTime time2;

    private LocalDateTime time3;

    private LocalDateTime time4;

    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public LocalDateTime getTime1() {
        return time1;
    }

    public void setTime1(LocalDateTime time1) {
        this.time1 = time1;
    }

    public LocalDateTime getTime2() {
        return time2;
    }

    public void setTime2(LocalDateTime time2) {
        this.time2 = time2;
    }

    public LocalDateTime getTime3() {
        return time3;
    }

    public void setTime3(LocalDateTime time3) {
        this.time3 = time3;
    }

    public LocalDateTime getTime4() {
        return time4;
    }

    public void setTime4(LocalDateTime time4) {
        this.time4 = time4;
    }

}
