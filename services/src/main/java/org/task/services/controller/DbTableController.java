package org.task.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.services.model.ColumnStatistics;
import org.task.services.model.TableStatistics;
import org.task.services.repository.TableDataRepository;

/**
 * Task 3:
 * The controller class which defines REST APi's for statistics about each column and each table.
 * 
 * @author Manu Mohan
 *
 */
@RestController
@RequestMapping("/api/v3")
public class DbTableController {

	@Autowired
	private TableDataRepository tableDataRepository;
	
	
	/**
	 * Single End point APi which gets the statistics about column in a table 
	 * @param tableName name of table
	 * @param columnName name of column
	 * @return {@link ColumnStatistics} column statistics contains min, max, avg, median value of the
	 *	        column. 
	 * 
	 */
	@GetMapping("/statistics/{tableName}/{columnName}")
	public ResponseEntity<ColumnStatistics> getColumnStatistics(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "columnName") String columnName) {

		ColumnStatistics columnStatistics = tableDataRepository.getColumnStatistics(tableName, columnName);
		return ResponseEntity.ok().body(columnStatistics);
	}
	
	/**
	 * API to get max, min, average and median value seperately
	 * @param tableName the name of the table
	 * @param columnName the name of the column in table
	 * @param statType possible values are max, min, avg, median.
	 * @return the value based on the statType requested.
	 */
	@GetMapping("/statistics/{tableName}/{columnName}/{statType}")
	public ResponseEntity<String> getColumnStatistics(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "columnName") String columnName, @PathVariable(value = "statType") String statType) {

		String result = "";
		switch (statType) {
		case "max":
			result = tableDataRepository.getMaxValue(tableName, columnName);
			break;

		case "min":
			result = tableDataRepository.getMinValue(tableName, columnName);
			break;

		case "avg":	
			result = tableDataRepository.getAverageValue(tableName, columnName);
			break;

		case "median":	
			result = tableDataRepository.getMedianValue(tableName, columnName);
			break;

		default:
			break;
		}
		return ResponseEntity.ok().body(result);
	}
	
	/**
	 * Single End point APi which gets statistics about a table
	 * @param tableName name of the table
	 * @return {@link TableStatistics} table statistics contain information about the no of records and attributes in table.
	 */
	@GetMapping("/statistics/table/{tableName}")
	public ResponseEntity<TableStatistics> getTableStatistics(@PathVariable(value = "tableName") String tableName){
		
		TableStatistics tableStatistics = tableDataRepository.getTableStatistics(tableName);
		return ResponseEntity.ok().body(tableStatistics);
	}
	
	/**
	 * API to get number of records and attributes in a table separately.
	 * @param tableName name of the table
	 * @param statType possible values are  recordcount,attrcount.
	 * @return the value based on the statType requested.
	 */
	@GetMapping("/statistics/table/{tableName}/{statType}")
	public ResponseEntity<String> getTableStatistics(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "statType") String statType){
		String result = "test";
		switch (statType) {
		case "recordcount":
			result = tableDataRepository.getNumberOfRecordsInTable(tableName);
			break;

		case "attrcount":
			int attributes = tableDataRepository.getNumberOfAttributesInTable(tableName);
			result = String.valueOf(attributes);
			break;

		default:
			break;
		}
		return ResponseEntity.ok().body(result);
	}
	
	
}

