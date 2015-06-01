package org.adclear.dbunit.json;

import java.net.UnknownHostException;

import lombok.Getter;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.database.IDatabaseConnection;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

/**
 * The {@link AbstractDatabaseTester} implementation for mongodb.
 *
 * @author fit
 */
@Getter
public class MongoDatabaseTester extends AbstractDatabaseTester {

    private Mongo    mongo;

    private MongoURI mongoURI;

    private DB       db;

    public MongoDatabaseTester(MongoURI uri) throws MongoException, UnknownHostException {
        this.mongoURI = uri;
        this.mongo = new Mongo(uri);
        this.db = this.mongo.getDB(this.mongoURI.getDatabase());
    }

    /*
     * (non-Javadoc)
     * @see org.dbunit.IDatabaseTester#getConnection()
     */
    public IDatabaseConnection getConnection() throws Exception {
        return new MongoDatabaseConnection(this.mongo, this.db);
    }
}
