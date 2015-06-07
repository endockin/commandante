package com.endockin.commandante.service.impl.scheduler.marathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DockerContainerInfoDto {

    public static class PortMapping {

        public PortMapping() {
        }

        @JsonProperty("hostPort")
        private int hostPort = 0;

        @JsonProperty("containerPort")
        private int containerPort = 0;

        @JsonProperty("servicePort")
        private int servicePort = 0;

        @JsonProperty("protocol")
        private Protocol protocol = Protocol.tcp;

        public int getHostPort() {
            return hostPort;
        }

        public void setHostPort(int hostPort) {
            this.hostPort = hostPort;
        }

        public int getContainerPort() {
            return containerPort;
        }

        public void setContainerPort(int containerPort) {
            this.containerPort = containerPort;
        }

        public int getServicePort() {
            return servicePort;
        }

        public void setServicePort(int servicePort) {
            this.servicePort = servicePort;
        }

        public Protocol getProtocol() {
            return protocol;
        }

        public void setProtocol(Protocol protocol) {
            this.protocol = protocol;
        }

        @Override
        public String toString() {
            return "PortMapping{" + "hostPort=" + hostPort + ", containerPort=" + containerPort + ", servicePort=" + servicePort + ", protocol=" + protocol + '}';
        }

    }

    public enum Protocol {

        tcp("tcp"), udp("udp");

        private final String value;

        private Protocol(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    @JsonProperty("image")
    private String image;

    @JsonProperty("portMappings")
    private List<PortMapping> portMappings;

    @JsonProperty("network")
    private String networkType = "BRIDGE";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    public void setPortMappings(List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    @Override
    public String toString() {
        return "DockerContainerInfoDto{" + "image=" + image + ", portMappings=" + portMappings + ", networkType=" + networkType + '}';
    }

}
