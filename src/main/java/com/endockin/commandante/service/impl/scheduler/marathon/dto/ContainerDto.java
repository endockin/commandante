package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContainerDto {

  public ContainerDto() {
  }

  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "ContainerDto{" +
      "type='" + type + '\'' +
      '}';
  }
}
