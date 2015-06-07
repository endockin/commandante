package com.endockin.commandante.model;

import java.util.Date;
import java.util.List;

public class Ship {

    private String appId;
    private String host;
    private String id;
    private List<Integer> ports;
    private Date stagedAt;
    private Date startedAt;
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
        return "Ship{" + "appId=" + appId + ", host=" + host + ", id=" + id + ", ports=" + ports + ", stagedAt=" + stagedAt + ", startedAt=" + startedAt + ", version=" + version + '}';
    }

}
