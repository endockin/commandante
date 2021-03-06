package com.endockin.commandante.model;

import java.util.List;

public abstract class Fleet {

    private String id;
    private String command;
    private List<String> labels;
    private Integer instanceNumber;
    private Double cpu;
    private Double memory;
    private List<Integer> ports;
    private List<Ship> ships;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(Integer instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "Fleet{" + "id=" + id + ", command=" + command + ", labels=" + labels + ", instanceNumber=" + instanceNumber + ", cpu=" + cpu + ", memory=" + memory + ", ports=" + ports + ", ships=" + ships + '}';
    }

    public abstract String getType();

}
