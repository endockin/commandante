package com.endockin.commandante.model;

public class DockerFleet extends Fleet {

    public static final String FLEET_TYPE = "DOCKER";

    private String image;

    @Override
    public String getType() {
        return FLEET_TYPE;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DockerFleet{" + "image=" + image + ", " + super.toString() + '}';
    }

}
