package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.endockin.commandante.service.impl.scheduler.marathon.dto.internal.MarathonApp;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AppsDto {

    @JsonProperty("apps")
    private List<MarathonApp> marathonApps;

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
