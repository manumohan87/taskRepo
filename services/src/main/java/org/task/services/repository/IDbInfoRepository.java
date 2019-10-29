package org.task.services.repository;


import java.sql.SQLException;
import java.util.List;

import org.task.services.model.TableData;

/**
 * Task 2: The repository interface which declares methods to communicate with database for browsing structure and data using database connection. 
 * @author Manu Mohan
 *
 */
public interface IDbInfoRepository {
	

	/**
	 * Gets all the tables in database
	 * @param userName name of user
	 * @param password password
	 * @param dataBaseName name of database
	 * @return the list of table names
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> getTablesInfo(String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException;
	

	/**
	 * Gets all the schema defined in database.
	 * @param userName name of user
	 * @param password password
	 * @param dataBaseName name of database
	 * @return the list of schema names
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> getSchemaInfo(String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * Gets all the columns defined in the table
	 * @param tableName name of table
	 * @param userName  name of user
	 * @param password password
	 * @param dataBaseName name of database
	 * @return the list of column names
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> getColumnNamesOfTable(String tableName,String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException;
	

	/**
	 * Gets the Data preview of the table.
	 * @param tableName the name of the table 
	 * @param userName name of user
	 * @param password password
	 * @param dataBaseName name of database
	 * @return {@link TableData}  table data contains data type , primary key , column name information of table.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TableData> getTableData(String tableName,String userName, String password, String dataBaseName) throws ClassNotFoundException, SQLException;
	
	
}
