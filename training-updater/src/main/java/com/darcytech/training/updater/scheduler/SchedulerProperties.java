package com.darcytech.training.updater.scheduler;

public class SchedulerProperties {

    private int workerSize = 200;

    private int schedulerSize = 50;

    public int getWorkerSize() {
        return workerSize;
    }

    public void setWorkerSize(int workerSize) {
        this.workerSize = workerSize;
    }

    public int getSchedulerSize() {
        return schedulerSize;
    }

    public void setSchedulerSize(int schedulerSize) {
        this.schedulerSize = schedulerSize;
    }

}
