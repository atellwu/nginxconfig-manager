package com.yeahmobi.loadbalance_manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.code.morphia.annotations.Id;

public class ConfigGen extends BaseEntity {

    // { fileName:"", version:"", url:"", md5:"", lastUpdateTime:"" }
    @Id
    @JSONField(serialize = false, deserialize = false)
    private Long   id;

    // global和vhost需要分module
    private String module;

    private String md5;

    private String content;

    private Type   type;

    // upstream需要每个region一份
    private String region;

    // vhost需要分name
    private String vhostName;

    // private String upstreamName;

    public static enum Type {
        GLOBAL, VHOST, UPSTREAM
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // @Override
    // @JSONField(name = "version")
    // public Long getId() {
    // return super.getId();
    // }
    //
    // @Override
    // @JSONField(name = "version")
    // public void setId(Long id) {
    // super.setId(id);
    // }
    //
    // @Override
    // @JSONField(serialize = false)
    // public Long getVersion() {
    // return super.getVersion();
    // }
    //
    // @Override
    // @JSONField(deserialize = false)
    // public void setVersion(long version) {
    // super.setVersion(version);
    // }

    public String getModule() {
        return this.module;
    }

    public void setModule(String fileName) {
        this.module = fileName;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVhostName() {
        return this.vhostName;
    }

    public void setVhostName(String vhostName) {
        this.vhostName = vhostName;
    }
    //
    // public String getUpstreamName() {
    // return this.upstreamName;
    // }
    //
    // public void setUpstreamName(String upstreamName) {
    // this.upstreamName = upstreamName;
    // }

}
