package com.yeahmobi.loadbalance_manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 */
@Service
public class MongoSeqGeneratorServiceImpl implements SeqGeneratorService {

    private final static String COLUMN_SEQ      = "seq";
    private final static String COLUMN_CATEGORY = "category";
    private final static String COLLECTION_SEQ  = "_seq";

    @Autowired
    private MongoClient         mongoClient;

    public long nextSeq(String category) {
        if ((category == null) || "".equals(category)) {
            throw new IllegalArgumentException("category can't be null or empty");
        }
        DBObject update = new BasicDBObject("$inc", new BasicDBObject(COLUMN_SEQ, 1L));
        DBObject query = new BasicDBObject(COLUMN_CATEGORY, category);
        return (Long) this.mongoClient.getMongo().getDB(this.mongoClient.getDbName()).getCollection(COLLECTION_SEQ).findAndModify(query,
                                                                                                                                  null,
                                                                                                                                  null,
                                                                                                                                  false,
                                                                                                                                  update,
                                                                                                                                  true,
                                                                                                                                  true).get(COLUMN_SEQ);
    }

}
