package com.darcytech.training.utils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CyclicBean1 {

    private String id;

    private CyclicBean2 bean2;

    public CyclicBean1(String id, CyclicBean2 bean2) {
        this.id = id;
        this.bean2 = bean2;
        this.bean2.setBean1(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CyclicBean2 getBean2() {
        return bean2;
    }

    public void setBean2(CyclicBean2 bean2) {
        this.bean2 = bean2;
    }

}
