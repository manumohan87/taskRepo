package org.task.services.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.services.model.ColumnStatistics;
import org.task.services.model.DbUser;
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
	 * @param {@link DbUser}
	 * @param tableName name of table
	 * @param columnName name of column
	 * @return {@link ColumnStatistics} column statistics contains min, max, avg, median value of the
	 *	        column. 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	@PostMapping("/statistics/{tableName}/{columnName}")
	public ResponseEntity<ColumnStatistics> getColumnStatistics(@Valid @RequestBody DbUser user,@PathVariable(value = "tableName") String tableName, @PathVariable(value = "columnName") String columnName) throws ClassNotFoundException, SQLException {

		ColumnStatistics columnStatistics = tableDataRepository.getColumnStatistics(user, tableName, columnName);
		return ResponseEntity.ok().body(columnStatistics);
	}
	
	
	
	/**
	 * Single End point APi which gets statistics about a table
	 * @param {@link DbUser}
	 * @param tableName name of the table
	 * @return {@link TableStatistics} table statistics contain information about the no of records and attributes in table.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@PostMapping("/statistics/table/{tableName}")
	public ResponseEntity<TableStatistics> getTableStatistics(@Valid @RequestBody DbUser user,@PathVariable(value = "tableName") String tableName) throws ClassNotFoundException, SQLException{
		
		TableStatistics tableStatistics = tableDataRepository.getTableStatistics(user, tableName);
		return ResponseEntity.ok().body(tableStatistics);
	}
}

