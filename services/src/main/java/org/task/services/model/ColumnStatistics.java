package org.task.services.model;

/**
 * Task 3: 
 * The class to represent table statistics
 * @author Manu Mohan
 *
 */
public class ColumnStatistics {
	
	private String minValue;
	private String maxValue;
	private String avgValue;
	private String medianValue;
	
	/**
	 * Gets the minimum value
	 * @return the minimum value in column
	 */
	public String getMinValue() {
		return minValue;
	}
	
	/**
	 * Sets the minimum value in column 
	 * @param minValue the minimum value
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * Gets the maximum value in column
	 * @return the maximum value in column
	 */
	public String getMaxValue() {
		return maxValue;
	}
	
	/**
	 * Sets the maximum value in column
	 * @param maxValue the maximum value
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * Gets the median value in the column
	 * @return the median value in the column
	 */
	public String getMedianValue() {
		return medianValue;
	}
	
	/**
	 * Sets the median value in the column
	 * @param medianValue the median value in the column
	 */
	public void setMedianValue(String medianValue) {
		this.medianValue = medianValue;
	}

	/**
	 * Gets the average value in the column
	 * @return average value in the column
	 */
	public String getAvgValue() {
		return avgValue;
	}
	
	/**
	 * Sets the average value in the column
	 * @param avgValue average value in the column
	 */
	public void setAvgValue(String avgValue) {
		this.avgValue = avgValue;
	}
	
	
}
