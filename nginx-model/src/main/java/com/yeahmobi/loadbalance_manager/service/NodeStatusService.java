package com.yeahmobi.loadbalance_manager.service;

import java.util.Date;
import java.util.List;

import com.yeahmobi.loadbalance_manager.model.NodeStatus;

public interface NodeStatusService {

    /** 将所有记录更新为“待更新” */
    void updateAll(NodeStatus.State state);

    /** 将所有region记录更新为“待更新” */
    void update(String regionName, NodeStatus.State state);

    /** 更新“心跳时间” */
    void updateHeartbeat(String regionName, String nodeId, Date lastHeartbeatTime);

    /**
     * 新增或更新某个region&nodeId的“状态”
     */
    void save(String regionName, String nodeId, NodeStatus.State state, String detail);

    /** 查询某个region&nodeId的“状态” */
    NodeStatus get(String regionName, String nodeId);

    /** 列出所有region&nodeId的“状态” */
    List<NodeStatus> listAll();

}
