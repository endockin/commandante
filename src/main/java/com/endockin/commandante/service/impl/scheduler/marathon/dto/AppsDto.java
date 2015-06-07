package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AppsDto {

    
    @JsonProperty("apps")
    private List<MarathonApp> marathonApps;

    public AppsDto() {

    }

    public List<MarathonApp> getMarathonApps() {
        return marathonApps;
    }

    public void setMarathonApps(List<MarathonApp> marathonApps) {
        this.marathonApps = marathonApps;
    }

    @Override
    public String toString() {
        return "AppsDto{" + "marathonApps=" + marathonApps + '}';
    }

}
