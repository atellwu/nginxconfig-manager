package com.yeahmobi.loadbalance_manager.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexes;

/**
 * nginx节点的状态
 *
 * @author atell
 */
@Indexes(@Index(name = "region_nodeId", value = "region,nodeId", unique = true))
public class NodeStatus extends BaseEntity {

    @Id
    @JSONField(serialize = false, deserialize = false)
    private Long   id;

    private String region;

    private String nodeId;

    private State  state;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date   lastStatusUpdateTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date   lastHeartbeatTime;

    private String detail;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static enum State {
        TOBE_UPDATED, UPDATING, UPDATE_SUCCESS, UPDATE_FAILED
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getLastStatusUpdateTime() {
        return this.lastStatusUpdateTime;
    }

    public void setLastStatusUpdateTime(Date lastStatusUpdateTime) {
        this.lastStatusUpdateTime = lastStatusUpdateTime;
    }

    public Date getLastHeartbeatTime() {
        return this.lastHeartbeatTime;
    }

    public void setLastHeartbeatTime(Date lastHeartbeatTime) {
        this.lastHeartbeatTime = lastHeartbeatTime;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
