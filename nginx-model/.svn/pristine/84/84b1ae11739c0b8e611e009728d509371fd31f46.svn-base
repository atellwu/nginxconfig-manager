package com.yeahmobi.loadbalance_manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.yeahmobi.loadbalance_manager.common.MD5Util;
import com.yeahmobi.loadbalance_manager.dao.ConfigGenDao;
import com.yeahmobi.loadbalance_manager.model.ConfigGen;
import com.yeahmobi.loadbalance_manager.service.ConfigGenService;

@Service
public class ConfigGenServiceImpl implements ConfigGenService {

    @Autowired
    private ConfigGenDao dao;

    public ConfigGen getGlobal(long codegenId) {
        // Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        // q.field("module").equal(module);
        // q.field("type").equal(ConfigGen.Type.GLOBAL);
        // q.field("version").equal(version);
        //
        // QueryResults<ConfigGen> result = this.dao.find(q);
        // List<ConfigGen> list = result.asList();
        //
        // if ((list != null) && (list.size() > 0)) {
        // return list.get(0);
        // }

        return this.dao.get(codegenId);
    }

    public ConfigGen getUpstream(long codegenId) {
        // Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        // q.field("type").equal(ConfigGen.Type.UPSTREAM);
        // q.field("region").equal(region);
        // q.field("version").equal(version);
        //
        // QueryResults<ConfigGen> result = this.dao.find(q);
        // List<ConfigGen> list = result.asList();
        //
        // if ((list != null) && (list.size() > 0)) {
        // return list.get(0);
        // }

        return this.dao.get(codegenId);
    }

    public ConfigGen getVhost(long codegenId) {
        // Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        // q.field("type").equal(ConfigGen.Type.VHOST);
        // q.field("vhostName").equal(vhostName);
        // q.field("module").equal(module);
        // q.field("version").equal(version);
        //
        // QueryResults<ConfigGen> result = this.dao.find(q);
        // List<ConfigGen> list = result.asList();
        //
        // if ((list != null) && (list.size() > 0)) {
        // return list.get(0);
        // }
        //
        // return null;

        return this.dao.get(codegenId);
    }

    public void addGlobal(String module, String content) {
        ConfigGen configGen = new ConfigGen();
        configGen.setContent(content);
        configGen.setModule(module);
        configGen.setType(ConfigGen.Type.GLOBAL);
        configGen.setMd5(MD5Util.generateMD5(content));

        this.dao.save(configGen);
    }

    public void addUpstream(String region, String content) {
        ConfigGen configGen = new ConfigGen();
        configGen.setContent(content);
        configGen.setType(ConfigGen.Type.UPSTREAM);
        configGen.setRegion(region);
        configGen.setContent(content);
        configGen.setMd5(MD5Util.generateMD5(content));

        this.dao.save(configGen);
    }

    public void addVhost(String vhostName, String module, String content) {
        ConfigGen configGen = new ConfigGen();
        configGen.setType(ConfigGen.Type.VHOST);
        configGen.setVhostName(vhostName);
        configGen.setModule(module);
        configGen.setContent(content);
        configGen.setMd5(MD5Util.generateMD5(content));

        this.dao.save(configGen);
    }

    public void delLessThan(long id) {
        Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        q.field("id").lessThan(id);
        this.dao.deleteByQuery(q);
    }

    public ConfigGen getLastGlobal(String module) {
        Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        q.field("type").equal(ConfigGen.Type.GLOBAL);
        q.field("module").equal(module);
        q.order("-id");

        QueryResults<ConfigGen> result = this.dao.find(q);
        List<ConfigGen> list = result.asList();

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        }

        return null;
    }

    public ConfigGen getLastUpstream(String region) {
        Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        q.field("type").equal(ConfigGen.Type.UPSTREAM);
        q.field("region").equal(region);
        q.order("-id");

        QueryResults<ConfigGen> result = this.dao.find(q);
        List<ConfigGen> list = result.asList();

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        }

        return null;
    }

    public ConfigGen getLastVhost(String vhostName, String module) {
        Query<ConfigGen> q = this.dao.getDatastore().createQuery(ConfigGen.class);
        q.field("type").equal(ConfigGen.Type.VHOST);
        q.field("vhostName").equal(vhostName);
        q.field("module").equal(module);
        q.order("-id");

        QueryResults<ConfigGen> result = this.dao.find(q);
        List<ConfigGen> list = result.asList();

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        }

        return null;
    }

}
