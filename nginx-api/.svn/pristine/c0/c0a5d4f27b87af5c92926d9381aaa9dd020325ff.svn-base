/**
 * 
 */
package org.adclear.dbunit.json.operation;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;
import static org.hamcrest.Matchers.equalTo;

import java.sql.SQLException;

import org.adclear.dbunit.json.MongoDatabaseConnection;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * @author fit
 * 
 */
public class MongoDeleteOperation extends MongoOperation {

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
			Column primaryColumn = selectFirst(table.getTableMetaData()
					.getColumns(),
					having(on(Column.class).getColumnName(), equalTo("_id")));

			if (primaryColumn != null) {
				for (int i = 0; i < table.getRowCount(); i++) {
					Object primaryValue = table.getValue(i, primaryColumn.getColumnName());
					collection.remove(new BasicDBObject("_id", primaryValue));
				}
			}
		}
	}

}
