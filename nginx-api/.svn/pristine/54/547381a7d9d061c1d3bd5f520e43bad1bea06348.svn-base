package org.adclear.dbunit.json.operation;

import java.sql.SQLException;

import org.adclear.dbunit.json.MongoDatabaseConnection;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;

/**
 * @author fit
 * 
 */
public class MongoDeleteAllOperation extends MongoOperation {

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
			mongoDatabaseConnection.getDb().dropDatabase();
	}
}
