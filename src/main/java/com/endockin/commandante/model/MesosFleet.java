package com.endockin.commandante.model;

public class MesosFleet extends Fleet{

  public static final String FLEET_TYPE = "MESOS";

  @Override
  public String getType() {
    return FLEET_TYPE;
  }


}
