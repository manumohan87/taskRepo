package org.task.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.services.model.DbUser;
import org.task.services.model.TableData;
import org.task.services.repository.DbInfoRepository;

/**
 * Task 2:
 * The controller class which defines REST APi's for browsing structure and data using your stored database
 * connections
 * @author Manu Mohan
 *
 */
@RestController
@RequestMapping("/api/v2")
public class DbInfoController {

	@Autowired
	private DbInfoRepository dbInfoRepository;
	
	
	/**
	 * Gets all the tables in database
	 * @param user {@link DbUser} user connection information
	 * @return the list of table names
	 * @throws ClassNotFoundException class not found exception
	 * @throws SQLException sql exception
	 */
	@PostMapping("/tables")
	public ResponseEntity<List<String>> getAllTables(@Valid @RequestBody DbUser user) throws ClassNotFoundException, SQLException {

		List<String> tableList = dbInfoRepository.getTablesInfo(user.getUserName(), user.getPassword(), user.getDatabaseName());
		return ResponseEntity.ok().body(tableList);
	}
	
	
	/**
	 * Gets all the schema defined in database.
	 * @param user {@link DbUser} user connection information
	 * @return the list of schema names.
	 * @throws ClassNotFoundException class not found exception
	 * @throws SQLException sql exception
	 */
	@PostMapping("/schemas")
	public ResponseEntity<List<String>> getSchemas(@Valid @RequestBody DbUser user) throws ClassNotFoundException, SQLException{

		List<String> schemaList = dbInfoRepository.getSchemaInfo(user.getUserName(), user.getPassword(), user.getDatabaseName());
		return ResponseEntity.ok().body(schemaList);
	}
	
	
	/**
	 * Gets all the columns defined in the table
	 * @param tableName name of the table
	 * @param user {@link DbUser} user connection information
	 * @return the list of column names
	 * @throws ClassNotFoundException  class not found exception
	 * @throws SQLException sql exception
	 */
	@PostMapping("/columns/{tableName}")
	public ResponseEntity<List<String>> getColumns(@PathVariable(value = "tableName") String tableName , @Valid @RequestBody DbUser user) throws ClassNotFoundException, SQLException{

		List<String> columnList = dbInfoRepository.getColumnNamesOfTable(tableName,user.getUserName(), user.getPassword(), user.getDatabaseName());
		return ResponseEntity.ok().body(columnList);
	}
	
	
	/**
	 * Gets the Data preview of the table.
	 * @param tableName the name of the table 
	 * @param user {@link DbUser} user connection information
	 * @return {@link TableData}  table data contains data type , primary key , column name information of table.
	 * @throws ClassNotFoundException class not found exception
	 * @throws SQLException  sql exception
	 */
	@PostMapping("/data/{tableName}")
	public ResponseEntity<List<TableData>> getTableData(@PathVariable(value = "tableName") String tableName,@Valid @RequestBody DbUser user)  throws ClassNotFoundException, SQLException{

		List<TableData> tableDataList = dbInfoRepository.getTableData(tableName,user.getUserName(), user.getPassword(), user.getDatabaseName());
		return ResponseEntity.ok().body(tableDataList);
	}

	
}
