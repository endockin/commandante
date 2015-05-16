package com.endockin.commandante.model;

import java.util.List;

public abstract class Ship {

  private String id;
  private List<String> labels;
  private Integer instanceNumber;
  private Double cpu;
  private Double memory;
  private List<Integer> ports;

  public Ship(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public abstract String getType();

  @Override
  public String toString() {
    return "Ship{" +
      "id='" + id + '\'' +
      ", labels=" + labels +
      ", instanceNumber=" + instanceNumber +
      ", cpu=" + cpu +
      ", memory=" + memory +
      ", ports=" + ports +
      '}';
  }
}
