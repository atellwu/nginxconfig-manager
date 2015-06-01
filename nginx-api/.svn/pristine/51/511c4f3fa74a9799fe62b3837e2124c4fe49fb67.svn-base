package org.adclear.dbunit.json.operation;

import java.sql.SQLException;

import org.adclear.dbunit.json.MongoDatabaseConnection;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

/**
 * @author fit
 * 
 */
public class MongoInsertOperation extends MongoOperation {

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
			for (int i = 0; i < table.getRowCount(); i++) {
				DBObject dbObject = new BasicDBObject();
				for (Column column : table.getTableMetaData().getColumns()) {
					Object value = table.getValue(i, column.getColumnName());
					if (value != null) {
						dbObject.put(column.getColumnName(), value);
					}
				}

				// FIXME writeconcern should be configurable
				collection.insert(dbObject, WriteConcern.SAFE);
			}
		}
	}
}
