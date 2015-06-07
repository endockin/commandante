package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DockerContainerInfoDto extends ContainerDto {

    @JsonProperty("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
