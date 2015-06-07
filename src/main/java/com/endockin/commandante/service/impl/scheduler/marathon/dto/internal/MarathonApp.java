package com.endockin.commandante.service.impl.scheduler.marathon.dto.internal;

import com.endockin.commandante.service.impl.scheduler.marathon.dto.ContainerDto;

public class MarathonApp {

    private String id;
    private String cmd;
    private Integer instances;
    private Double cpus;
    private Double mem;
    private ContainerDto container;

    public MarathonApp() {

    }

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

    @Override
    public String toString() {
        return "AppDto{"
                + "id='" + id + '\''
                + ", cmd='" + cmd + '\''
                + ", instances=" + instances
                + ", cpus=" + cpus
                + ", mem=" + mem
                + ", container=" + container
                + '}';
    }
}
