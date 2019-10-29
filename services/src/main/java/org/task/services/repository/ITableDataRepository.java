package org.task.services.repository;

import org.task.services.model.ColumnStatistics;
import org.task.services.model.TableStatistics;

/**
 * Task 2: The repository interface which declares methods to communicate with database for statistics about each column and each table.
 * @author Manu Mohan
 *
 */
public interface ITableDataRepository {
	
	/**
	 * Gets the statistics about column in a table 
	 * @param tableName name of table
	 * @param columnName name of column
	 * @return {@link ColumnStatistics} column statistics contains min, max, avg, median value of the column. 
	 * 
	 */
	public ColumnStatistics getColumnStatistics(String tableName, String columnName);
	
	/**
	 * Gets the maximum value in column of table
	 * @param tableName table name 
	 * @param columnName column name
	 * @return the maximum value
	 */
	public String getMaxValue(String tableName, String columnName);
	
	/**
	 * Gets the minimum value in column
	 * @param tableName table name
	 * @param columnName column name
	 * @return the minimum value
	 */
	public String getMinValue(String tableName, String columnName);
	
	/**
	 * Gets the average value in column
	 * @param tableName table name
	 * @param columnName column name
	 * @return the average value in the column
	 */
	public String getAverageValue(String tableName, String columnName);
	
	/**
	 * Gets the median value in the column
	 * @param tableName table name
	 * @param columnName column name
	 * @return the median value in the column
	 */
	public String getMedianValue(String tableName, String columnName);
	
	/**
	 * Get the record count in the table
	 * @param tableName table name
	 * @return the number of rows in the table 
	 */
	public String getNumberOfRecordsInTable(String tableName);
	
	/**
	 * Gets the number of attributes in the column
	 * @param tableName table name
	 * @return the number of columns in the table
	 * 
	 */
	public Integer getNumberOfAttributesInTable(String tableName);
	
	/**
	 * Gets the statistics about a table
	 * @param tableName name of the table
	 * @return {@link TableStatistics}  
	 */
	public TableStatistics getTableStatistics(String tableName);
	
}
