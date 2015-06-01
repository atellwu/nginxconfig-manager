package com.yeahmobi.loadbalance_manager.service;

import java.util.List;

import com.yeahmobi.loadbalance_manager.model.Region;

public interface RegionService {

    /** 查询所有 */
    List<Region> listAll();

    /** 添加 */
    void add(Region region);

    /** 删除 */
    void del(String regionName);

    /** 是否存在 */
    boolean exists(String regionName);
}
