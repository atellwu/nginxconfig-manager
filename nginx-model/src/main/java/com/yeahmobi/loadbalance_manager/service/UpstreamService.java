package com.yeahmobi.loadbalance_manager.service;

import java.util.List;

import com.yeahmobi.loadbalance_manager.model.Upstream;

public interface UpstreamService {

    /** 添加upstream */
    void add(Upstream upstream);

    /** 更新upstream */
    void update(Upstream upstream);

    /** 查询upstream */
    Upstream get(String name);

    /** 删除upstream */
    void del(String name);

    /** 查询upstream */
    List<Upstream> listAll();

    /** 是否存在 */
    boolean exists(String name);
}
