package com.yeahmobi.loadbalance_manager.model;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.google.code.morphia.annotations.Id;
import com.yeahmobi.loadbalance_manager.annotation.DefaultValue;

public class Vhost extends BaseEntity {

    @Id
    @NotBlank
    private String       name;

    @DefaultValue(value = "80")
    private Integer      listenPort;

    @DefaultValue(value = "false")
    private Boolean      isDefaultServer;

    private List<String> serverNames;

    private String       upstreamName;

    private String       custom;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getListenPort() {
        return this.listenPort;
    }

    public void setListenPort(Integer listenPort) {
        this.listenPort = listenPort;
    }

    public Boolean getIsDefaultServer() {
        return this.isDefaultServer;
    }

    public void setIsDefaultServer(Boolean isDefaultServer) {
        this.isDefaultServer = isDefaultServer;
    }

    public List<String> getServerNames() {
        return this.serverNames;
    }

    public void setServerNames(List<String> serverNames) {
        this.serverNames = serverNames;
    }

    public String getUpstreamName() {
        return this.upstreamName;
    }

    public void setUpstreamName(String upstreamName) {
        this.upstreamName = upstreamName;
    }

    public String getCustom() {
        return this.custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

}
