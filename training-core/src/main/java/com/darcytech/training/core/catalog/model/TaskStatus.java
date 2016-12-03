package com.darcytech.training.core.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.darcytech.training.core.base.BaseJpaModel;

@Entity
public class TaskStatus extends BaseJpaModel<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskStep taskStep;

    @Column(updatable = false)
    private boolean system;

    @JoinColumn(updatable = false)
    @ManyToOne
    private TaskType taskType;

    public TaskStatus() {
    }

    public TaskStatus(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStep getTaskStep() {
        return taskStep;
    }

    public void setTaskStep(TaskStep taskStep) {
        this.taskStep = taskStep;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

}
