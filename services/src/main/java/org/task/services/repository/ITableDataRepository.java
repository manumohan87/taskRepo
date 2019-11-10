package org.task.services.repository;

import java.sql.SQLException;

import org.task.services.model.ColumnStatistics;
import org.task.services.model.DbUser;
import org.task.services.model.TableStatistics;

/**
 * Task 2: The repository interface which declares methods to communicate with database for statistics about each column and each table.
 * @author Manu Mohan
 *
 */
public interface ITableDataRepository {
	

	/**
	 * Gets the statistics about column in a table 
	 * @param user {@link DbUser}
	 * @param tableName name of table
	 * @param columnNamename of column
	 * @return {@link ColumnStatistics} column statistics contains min, max, avg, median value of the column. 
	 * @throws SQLException sql exception
	 * @throws ClassNotFoundException class not found exception
	 */
	public ColumnStatistics getColumnStatistics(DbUser user, String tableName, String columnName) throws SQLException, ClassNotFoundException;
	
	

	/**
	 * Gets the statistics about a table
	 * @param user {@link DbUser}
	 * @param tableName name of the table
	 * @return  {@link TableStatistics}  
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TableStatistics getTableStatistics(DbUser user, String tableName) throws SQLException, ClassNotFoundException;
	
}
