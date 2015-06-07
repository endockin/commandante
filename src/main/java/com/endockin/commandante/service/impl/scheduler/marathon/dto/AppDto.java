package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppDto {

    @JsonProperty("app")
    private MarathonApp marathonApp;

    public AppDto() {
    }

    public MarathonApp getMarathonApp() {
        return marathonApp;
    }

    public void setMarathonApp(MarathonApp marathonApp) {
        this.marathonApp = marathonApp;
    }

    @Override
    public String toString() {
        return "AppDto{" + "marathonApp=" + marathonApp + '}';
    }

}
