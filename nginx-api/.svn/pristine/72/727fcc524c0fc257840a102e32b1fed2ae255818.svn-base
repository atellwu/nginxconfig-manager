package org.adclear.dbunit.json.operation;

import java.sql.SQLException;

import org.adclear.dbunit.json.MongoDatabaseConnection;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * @author fit
 * 
 */
public class MongoTruncateOperation extends MongoOperation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dbunit.operation.DatabaseOperation#execute(org.dbunit.database.
	 * IDatabaseConnection, org.dbunit.dataset.IDataSet)
	 */
	@Override
	public void execute(MongoDatabaseConnection connection, IDataSet dataSet)
			throws DatabaseUnitException, SQLException {
		MongoDatabaseConnection mongoDatabaseConnection = (MongoDatabaseConnection) connection;
		ITableIterator iterator = dataSet.iterator();
		while (iterator.next()) {
			ITable table = iterator.getTable();
			DBCollection collection = mongoDatabaseConnection.getDb()
					.getCollection(table.getTableMetaData().getTableName());
			
			collection.remove(new BasicDBObject());
		}
	}

}
