package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerDto {

    @JsonProperty("type")
    private String type;
    
    @JsonProperty("docker")
    private DockerContainerInfoDto dockerContainerInfoDto;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DockerContainerInfoDto getDockerContainerInfoDto() {
        return dockerContainerInfoDto;
    }

    public void setDockerContainerInfoDto(DockerContainerInfoDto dockerContainerInfoDto) {
        this.dockerContainerInfoDto = dockerContainerInfoDto;
    }

    @Override
    public String toString() {
        return "ContainerDto{" + "type=" + type + ", dockerContainerInfoDto=" + dockerContainerInfoDto + '}';
    }

}
