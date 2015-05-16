package com.endockin.commandante.model;

public class MesosShip extends Ship{

  public static final String SHIP_TYPE = "DOCKER";

  public MesosShip(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return SHIP_TYPE;
  }


}
