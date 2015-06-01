package org.adclear.dbunit.json.operation;

import java.sql.SQLException;

import org.adclear.dbunit.json.MongoDatabaseConnection;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;

public abstract class MongoOperation extends DatabaseOperation {
	public static final DatabaseOperation NONE = new DummyOperation();
	public static final DatabaseOperation INSERT = new MongoInsertOperation();
	public static final DatabaseOperation UPDATE = new MongoUpdateOperation();
	public static final DatabaseOperation REFRESH = new MongoRefreshOperation();
	public static final DatabaseOperation DELETE = new MongoDeleteOperation();
	public static final DatabaseOperation DELETE_ALL = new MongoDeleteAllOperation();
	public static final DatabaseOperation TRUNCATE_TABLE = new MongoTruncateOperation();
	public static final DatabaseOperation CLEAN_INSERT = new CompositeOperation(
			DELETE_ALL, INSERT);

	private static class DummyOperation extends DatabaseOperation {
		public void execute(IDatabaseConnection connection, IDataSet dataSet) {
		}
	}

	@Override
	public void execute(IDatabaseConnection connection, IDataSet dataSet)
			throws DatabaseUnitException, SQLException {
		if (connection instanceof MongoDatabaseConnection) {
			execute((MongoDatabaseConnection) connection, dataSet);
		}
	}

	public abstract void execute(MongoDatabaseConnection connection,
			IDataSet dataSet) throws DatabaseUnitException, SQLException;
}
