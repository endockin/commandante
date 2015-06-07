package com.endockin.commandante.model;

public class DockerShip extends Ship {

    public static final String SHIP_TYPE = "DOCKER";

    private String image;


    @Override
    public String getType() {
        return SHIP_TYPE;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DockerShip{"
                + "image='" + image + '\''
                + "} " + super.toString();
    }
}
