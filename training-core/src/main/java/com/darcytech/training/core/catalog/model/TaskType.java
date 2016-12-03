package com.darcytech.training.core.catalog.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class TaskType {

    @Id
    @GeneratedValue
    private Integer id;

    private long userId;

    private String name;

    private int typeOrder;

    private String fieldSettings;

    private boolean system;

    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "status_order")
    private List<TaskStatus> taskStatuses;

    public TaskType() {
    }

    public TaskType(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(int typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getFieldSettings() {
        return fieldSettings;
    }

    public void setFieldSettings(String fieldSettings) {
        this.fieldSettings = fieldSettings;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public List<TaskStatus> getTaskStatuses() {
        return taskStatuses == null ? Collections.emptyList():Collections.unmodifiableList(taskStatuses);
    }

    public void setTaskStatuses(List<TaskStatus> taskStatuses) {
        this.taskStatuses = taskStatuses;
    }

    public void addTaskStatus(TaskStatus taskStatus) {
        taskStatus.setTaskType(this);
        if (taskStatuses == null) {
            taskStatuses = new ArrayList<>();
        }
        taskStatuses.add(taskStatus);
    }

    public void clearTaskStatuses() {
        if (taskStatuses != null) {
            taskStatuses.clear();
        }
    }
}
