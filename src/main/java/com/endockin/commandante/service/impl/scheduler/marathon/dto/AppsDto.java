package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import java.util.List;

public class AppsDto {

  private List<AppDto> apps;

  public AppsDto() {

  }

  public List<AppDto> getApps() {
    return apps;
  }

  public void setApps(List<AppDto> apps) {
    this.apps = apps;
  }

  @Override
  public String toString() {
    return "AppsDto{" +
      "apps=" + apps +
      '}';
  }
}
