package com.darcytech.training.core.node.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CronTrigger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String triggerName;

    private String cronExpression;

    private boolean enabled;

    private LocalDateTime lastModified;

    private LocalDateTime lastFiredTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public LocalDateTime getLastFiredTime() {
        return lastFiredTime;
    }

    public void setLastFiredTime(LocalDateTime lastFiredTime) {
        this.lastFiredTime = lastFiredTime;
    }

}
