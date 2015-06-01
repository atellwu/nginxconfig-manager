package com.yeahmobi.loadbalance_manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahmobi.loadbalance_manager.dao.GlobalDao;
import com.yeahmobi.loadbalance_manager.model.Global;
import com.yeahmobi.loadbalance_manager.service.GlobalService;

@Service
public class GlobalServiceImpl implements GlobalService {

    private static final long ID = 1L;

    @Autowired
    private GlobalDao         dao;

    public void update(Global entity) {
        entity.setId(ID);
        this.dao.save(entity);
    }

    public Global get() {
        return this.dao.get(ID);
    }

}
