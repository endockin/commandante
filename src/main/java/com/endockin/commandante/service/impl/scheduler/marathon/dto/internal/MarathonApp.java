package com.endockin.commandante.service.impl.scheduler.marathon.dto.internal;

import com.endockin.commandante.service.impl.scheduler.marathon.dto.ContainerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MarathonApp {

    @JsonProperty("id")
    private String id;

    @JsonProperty("cmd")
    private String cmd;

    @JsonProperty("instances")
    private Integer instances;

    @JsonProperty("cpus")
    private Double cpus;

    @JsonProperty("mem")
    private Double mem;

    @JsonProperty("container")
    private ContainerDto container;

    @JsonProperty("tasks")
    private List<MarathonTask> tasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Integer getInstances() {
        return instances;
    }

    public void setInstances(Integer instances) {
        this.instances = instances;
    }

    public Double getCpus() {
        return cpus;
    }

    public void setCpus(Double cpus) {
        this.cpus = cpus;
    }

    public Double getMem() {
        return mem;
    }

    public void setMem(Double mem) {
        this.mem = mem;
    }

    public ContainerDto getContainer() {
        return container;
    }

    public void setContainer(ContainerDto container) {
        this.container = container;
    }

    public List<MarathonTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<MarathonTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "MarathonApp{" + "id=" + id + ", cmd=" + cmd + ", instances=" + instances + ", cpus=" + cpus + ", mem=" + mem + ", container=" + container + ", tasks=" + tasks + '}';
    }

}
