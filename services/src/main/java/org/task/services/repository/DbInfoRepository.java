package org.task.services.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.task.services.model.TableData;

/**
 * Task2 : The repository class which communicates with database for browsing structure and data using database connection.
 * @author Manu Mohan
 *
 */
@Repository
public class DbInfoRepository implements IDbInfoRepository{

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getTablesInfo(String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException  {

		Connection connection = null;

		try {
			connection = getConnection(userName, password, dataBaseName);

			List<String> tableList = new ArrayList<String>();
			DatabaseMetaData metaData = connection.getMetaData();

			ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {
				String tableName =  tables.getString("TABLE_NAME");
				tableList.add(tableName);
			}
			return tableList;
		}finally {
			if(connection != null) {
				connection.close();
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getSchemaInfo(String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException {

		Connection connection = null;

		try {
			connection = getConnection(userName, password, dataBaseName);
			List<String> schemaList = new ArrayList<String>();
			DatabaseMetaData metaData = connection.getMetaData();

			ResultSet schemas = metaData.getSchemas();
			while (schemas.next()) {
				String schemaName = schemas.getString(1);
				schemaList.add(schemaName);
			}
			return schemaList;
		}finally {
			if(connection != null) {
				connection.close();
			}
		}
	}

	/**s
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getColumnNamesOfTable(String tableName,String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException {

		Connection connection = null;

		try {
			connection = getConnection(userName, password, dataBaseName);
			List<String> columnList = new ArrayList<String>();
			DatabaseMetaData metaData = connection.getMetaData();

			ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");

			while (columns.next()) {
				String columnName =  columns.getString("COLUMN_NAME");
				columnList.add(columnName);
			}
			return columnList;
		}finally {
			if(connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TableData> getTableData(String tableName,String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException {

		Connection connection = null;

		try {
			connection = getConnection(userName, password, dataBaseName);

			DatabaseMetaData metaData = connection.getMetaData();

			Map<String, TableData> tableDataMap = new HashMap<String, TableData>();


			// Getting column information
			ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");

			while (columns.next()) {
				String columnName =  columns.getString("COLUMN_NAME");
				String columnType = columns.getString("TYPE_NAME");

				TableData tableData = new TableData();
				tableData.setColumnName(columnName);
				tableData.setColumnType(columnType);
				tableDataMap.put(columnName, tableData);
			}

			//Getting primary key information
			ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
			while (primaryKeys.next()) {

				String columnName = primaryKeys.getString("COLUMN_NAME");
				TableData tableData = tableDataMap.get(columnName);

				if(null != tableData) {
					tableData.setIsPrimaryKey(true);
				}
			}

			return new ArrayList<TableData>(tableDataMap.values());
		}finally {
			if(connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Gets the connection with the database
	 * @param userName name of user
	 * @param password password of the user
 	 * @param databaseName name of database
	 * @return {@link Connection} sql connection
	 * @throws ClassNotFoundException class not found exception
	 * @throws SQLException sql exception
	 */
	private Connection getConnection(String userName, String password, String databaseName) throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");

		Connection connection  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+databaseName, userName, password);

		return connection;
	}

}
