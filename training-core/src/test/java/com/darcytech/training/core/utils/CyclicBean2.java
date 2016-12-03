package com.darcytech.training.core.utils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CyclicBean2 {

    private String id;

    private CyclicBean1 bean1;

    private CyclicBean3 cyclicBean3;

    public CyclicBean2(String id, CyclicBean3 cyclicBean3) {
        this.id = id;
        this.cyclicBean3 = cyclicBean3;
        this.cyclicBean3.addBean2(this);
    }

    public void setBean1(CyclicBean1 bean1) {
        this.bean1 = bean1;
    }

    public CyclicBean1 getBean1() {
        return bean1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CyclicBean3 getCyclicBean3() {
        return cyclicBean3;
    }

    public void setCyclicBean3(CyclicBean3 cyclicBean3) {
        this.cyclicBean3 = cyclicBean3;
    }

}
