package com.darcytech.training.core.utils;

import java.util.ArrayList;
import java.util.List;

public class CyclicBean3 {

    private String id;

    private List<CyclicBean2> bean2List;

    public CyclicBean3(String id) {
        this.id = id;
        this.bean2List = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CyclicBean2> getBean2List() {
        return bean2List;
    }

    public void addBean2(CyclicBean2 bean2) {
        bean2List.add(bean2);
    }

}
