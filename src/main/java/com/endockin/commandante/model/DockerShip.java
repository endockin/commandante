package com.endockin.commandante.model;

public class DockerShip extends Ship {

  public static final String SHIP_TYPE = "DOCKER";

  private String image;

  public DockerShip(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return SHIP_TYPE;
  }

  @Override
  public String toString() {
    return "DockerShip{" +
      "image='" + image + '\'' +
      "} " + super.toString();
  }
}
