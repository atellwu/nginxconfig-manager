package com.yeahmobi.loadbalance_manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.QueryResults;
import com.yeahmobi.loadbalance_manager.annotation.DefaultValueUtil;
import com.yeahmobi.loadbalance_manager.dao.RegionDao;
import com.yeahmobi.loadbalance_manager.exception.ServerException;
import com.yeahmobi.loadbalance_manager.model.Region;
import com.yeahmobi.loadbalance_manager.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao dao;

    public List<Region> listAll() {
        QueryResults<Region> result = this.dao.find();
        List<Region> list = result.asList();

        return list;

    }

    public void add(Region region) {
        // 设置默认值
        try {
            DefaultValueUtil.resolveDefaultValue(region);
        } catch (IllegalArgumentException e) {
            throw new ServerException("Error when resolving default value", e);
        } catch (IllegalAccessException e) {
            throw new ServerException("Error when resolving default value", e);
        }

        this.dao.save(region);
    }

    public void del(String regionName) {
        this.dao.deleteById(regionName);
    }

    public boolean exists(String regionName) {
        return this.dao.get(regionName) != null;
    }

}
