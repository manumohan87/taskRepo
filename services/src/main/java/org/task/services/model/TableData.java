package org.task.services.model;

/**
 * Task 2: 
 * The class to represent data preview of the table
 * @author Manu Mohan
 *
 */
public class TableData {

	private String columnType;
	private String columnName;
	private Boolean isPrimaryKey = false;
	
	/**
	 * Gets the column data type
	 * @return the column data type
	 */
	public String getColumnType() {
		return columnType;
	}
	
	/**
	 * Sets the column data type
	 * @param dataType the column data type
	 */
	public void setColumnType(String dataType) {
		this.columnType = dataType;
	}
	
	/**
	 * Gets the column name
	 * @return the column name
	 */
	public String getColumnName() {
		return columnName;
	}
	
	/**
	 * Sets the column name
	 * @param columnName name of column
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Returns if its a primary key
	 * @return true if it is a primary key else false
	 */
	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}
	
	/**
	 * Sets the primary key flag
	 * @param isPrimaryKey true if primary key else false
	 */
	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
}
