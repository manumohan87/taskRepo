package org.task.services.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.task.services.model.ColumnStatistics;
import org.task.services.model.DbUser;
import org.task.services.model.TableStatistics;
import org.task.services.util.ComputeUtil;

/**
 * Task3 : The repository class which communicates with database for statistics about each column and each table.
 * @author Manu Mohan
 *
 */
@Repository
public class TableDataRepository implements ITableDataRepository{


	/**
	 * {@inheritDoc}}
	 */
	@Override
	public ColumnStatistics getColumnStatistics(DbUser user, String tableName, String columnName) throws SQLException, ClassNotFoundException {

		ColumnStatistics columnStatistics = new ColumnStatistics();

		Connection connection = null;
		try {
			connection = getConnection(user.getUserName(),user.getPassword(), user.getDatabaseName());

			columnStatistics.setMaxValue(getMaxValue(connection, tableName, columnName));
			columnStatistics.setMinValue(getMinValue(connection, tableName, columnName));
			columnStatistics.setAvgValue(getAverageValue(connection, tableName, columnName));
			columnStatistics.setMedianValue(getMedianValue(connection, tableName, columnName));
		}finally {
			if(connection != null) {
				connection.close();
			}
		}


		return columnStatistics;
	}


	/**
	 * Gets the maximum value in column of table
	 * @param connection connection to the database
	 * @param tableName table name 
	 * @param columnName column name
	 * @return the maximum value
	 */
	public String getMaxValue(Connection connection, String tableName, String columnName) throws SQLException {

		String maxValue = "";
		Statement statement = null;
		ResultSet rs = null;
		try {
			String query = "SELECT MAX("+columnName+") from "+tableName;

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			if(rs.next()) {
				maxValue = rs.getString(1);
			}
			return maxValue;
		}finally {

			if(rs != null) {
				rs.close();
			}

			if(statement != null) {
				statement.close();
			}
		}

	}


	/**
	 * Gets the average value in column
	 * @param connection connection to the database
	 * @param tableName table name
	 * @param columnName column name
	 * @return the average value in the column
	 */
	public String getMinValue(Connection connection, String tableName, String columnName) throws SQLException{

		String minValue = "";
		Statement statement = null;
		ResultSet rs = null;
		try {
			String query = "SELECT MIN("+columnName+") from "+tableName;

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			if(rs.next()) {
				minValue = rs.getString(1);
			}
			return minValue;
		}finally {

			if(rs != null) {
				rs.close();
			}

			if(statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Gets the average value in column
	 * @param connection connection to the database
	 * @param tableName table name
	 * @param columnName column name
	 * @return the average value in the column
	 */
	public String getAverageValue(Connection connection, String tableName, String columnName) throws SQLException{

		String avgValue = "";
		Statement statement = null;
		ResultSet rs = null;
		try {
			String query = "SELECT AVG("+columnName+") from "+tableName;

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			if(rs.next()) {
				avgValue = rs.getString(1);
			}
			return avgValue;
		}finally {

			if(rs != null) {
				rs.close();
			}

			if(statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Gets the median value in the column
	 * @param connection connection to the database
	 * @param tableName table name
	 * @param columnName column name
	 * @return the median value in the column
	 */
	public String getMedianValue(Connection connection, String tableName, String columnName) throws SQLException{

		Statement statement = null;
		ResultSet rs = null;
		List<Long> datas = new ArrayList<Long>();
		try {
			String query = "SELECT "+columnName+" from "+tableName;

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			while(rs.next()) {
				datas.add(rs.getLong(1));
			}
			return ComputeUtil.getInstance().getMedianValue(datas);
		}finally {

			if(rs != null) {
				rs.close();
			}

			if(statement != null) {
				statement.close();
			}
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableStatistics getTableStatistics(DbUser user, String tableName) throws SQLException, ClassNotFoundException {

		TableStatistics tableStatistics = new TableStatistics();
		Connection connection = null;
		try {
			connection = getConnection(user.getUserName(), user.getPassword(), user.getDatabaseName());
			tableStatistics.setAttributeCount(getNumberOfAttributesInTable(connection,tableName));
			tableStatistics.setRecordCount(getNumberOfRecordsInTable(connection,tableName));
			return tableStatistics;
		}finally {
			if(connection != null) {
				connection.close();
			}
		}


	}

	/**
	 * Gets the record count in the table
	 * @param connection connection to the database
	 * @param tableName table name
	 * @return the number of rows in the table 
	 */
	public String getNumberOfRecordsInTable(Connection connection, String tableName) throws SQLException {

		String rowCount = "";
		Statement statement = null;
		ResultSet rs = null;
		try {
			String query = "SELECT count(*) from "+tableName;

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			if(rs.next()) {
				rowCount = rs.getString(1);
			}
			return rowCount;
		}finally {

			if(rs != null) {
				rs.close();
			}

			if(statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Gets the number of attributes in the column
	 * @param connection connection to the database
	 * @param tableName table name
	 * @return the number of columns in the table
	 * 
	 */
	public Integer getNumberOfAttributesInTable(Connection connection, String tableName) throws SQLException {

		int size = 0;
		ResultSet columns = null;
		try {

			DatabaseMetaData metaData  = connection.getMetaData();

			columns = metaData.getColumns(null,  null,  tableName, "%");

			if(columns != null) {
				columns.last();
				size = columns.getRow();
			}
			return size;
		}finally {

			if(columns != null) {
				columns.close();
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
