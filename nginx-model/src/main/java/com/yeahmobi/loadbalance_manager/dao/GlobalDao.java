package com.yeahmobi.loadbalance_manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahmobi.loadbalance_manager.model.Global;

/**
 */
@Service
public class GlobalDao extends MongoBaseDao<Global, Long> {

    @Autowired
    public GlobalDao(MongoClient mongoClient) {
        super(mongoClient.getDatastore());
        // at application start
        // map classes before calling with morphia map* methods
        mongoClient.getMorphia().map(Global.class);
        mongoClient.getDatastore().ensureCaps(); // creates capped collections from @Entity
        mongoClient.getDatastore().ensureIndexes(); // creates indexes from @Index annotations in your entities
    }

}
