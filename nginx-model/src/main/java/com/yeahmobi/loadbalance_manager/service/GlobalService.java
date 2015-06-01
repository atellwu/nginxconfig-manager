package com.yeahmobi.loadbalance_manager.service;

import com.yeahmobi.loadbalance_manager.model.Global;

public interface GlobalService {

    void update(Global config);

    Global get();

}
