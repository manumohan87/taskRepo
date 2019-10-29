package org.task.services.model;

/**
 * Task 3: 
 * The class to represent statistics of the table
 * @author Manu Mohan
 *
 */
public class TableStatistics {
	
	private String recordCount;
	private Integer attributeCount;
	
	/**
	 * Gets the number of records in the table
	 * @return the number of rows
	 */
	public String getRecordCount() {
		return recordCount;
	}
	
	/**
	 * Sets the number of records in the table
	 * @param recordCount the number of rows
	 */
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	
	/**
	 * Gets the number of attributes in the column
	 * @return the number of columns
	 */
	public Integer getAttributeCount() {
		return attributeCount;
	}
	
	/**
	 * Sets the number of attributes in the column
	 * @param attributeCount the number of columns
	 */
	public void setAttributeCount(Integer attributeCount) {
		this.attributeCount = attributeCount;
	}
	
	
}
