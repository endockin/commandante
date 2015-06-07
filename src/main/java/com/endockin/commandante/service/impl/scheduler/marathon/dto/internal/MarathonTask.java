package com.endockin.commandante.service.impl.scheduler.marathon.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class MarathonTask {

    @JsonProperty("appId")
    private String appId;
    @JsonProperty("host")
    private String host;
    @JsonProperty("id")
    private String id;
    @JsonProperty("ports")
    private List<Integer> ports;
    @JsonProperty("stagedAt")
    private Date stagedAt;
    @JsonProperty("startedAt")
    private Date startedAt;
    @JsonProperty("version")
    private Date version;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public Date getStagedAt() {
        return stagedAt;
    }

    public void setStagedAt(Date stagedAt) {
        this.stagedAt = stagedAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MarathonTask{" + "appId=" + appId + ", host=" + host + ", id=" + id + ", ports=" + ports + ", stagedAt=" + stagedAt + ", startedAt=" + startedAt + ", version=" + version + '}';
    }

}
