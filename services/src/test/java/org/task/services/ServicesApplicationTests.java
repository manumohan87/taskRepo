package org.task.services;


import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.task.services.model.ColumnStatistics;
import org.task.services.model.DbUser;
import org.task.services.model.TableData;
import org.task.services.model.TableStatistics;

/**
 * Test class for verifying the Rest APiS
 * @author Manu Mohan
 *
 */

@SpringBootTest(classes = ServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServicesApplicationTests {

	private static final String COLUMN_NAME_ID = "id";

	private static final String TABLE_NAME = "dbuser";

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	private String getRootUrl() {
		return "http://localhost:" + port;
	}


	/**
	 * Tests the Task1 to create , update , get user connection details
	 */
	@Test
	public void testCreateUser() {

		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("junitdb");
		dbUser.setUserName("junituser123");
		dbUser.setPassword("pas123#");

		restTemplate.postForObject(getRootUrl() + "/api/v1/users", dbUser, Map.class);

		testGetUsersByName(dbUser.getUserName());

		testGetAllUsers();

		testUpdateUser(dbUser.getUserName());
	}


	public void testGetAllUsers() {
		DbUser[] dbUsers = restTemplate.getForObject(getRootUrl() + "/api/v1/listusers/", DbUser[].class);
		Assert.assertTrue(dbUsers.length > 0);
	}


	public void testGetUsersByName(String userName) {
		DbUser user = restTemplate.getForObject(getRootUrl() + "/api/v1/listusers/"+userName, DbUser.class);
		Assert.assertTrue(user.getUserName().equals(userName));
	}


	public void testUpdateUser(String oldName) {


		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("junitdb");
		dbUser.setUserName("junituser23");
		dbUser.setPassword("pass123#");

		restTemplate.put(getRootUrl() + "/api/v1/users/"+oldName,dbUser);

		DbUser user = restTemplate.getForObject(getRootUrl() + "/api/v1/listusers/"+dbUser.getUserName(), DbUser.class);
		Assert.assertTrue(user.getUserName().equals(dbUser.getUserName()));

		restTemplate.delete(getRootUrl() + "/api/v1/deletuser/"+dbUser.getUserName());
	}

	/**
	 * Test get all tables
	 */
	@Test
	public void testGetAllTables() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		String[] tableList = restTemplate.postForObject(getRootUrl() + "/api/v2/tables", dbUser, String[].class);
		Assert.assertTrue(tableList.length > 0);
	}

	/**
	 * Tests get all schema in database
	 */
	@Test
	public void testGetSchemas() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		String[] schemaList = restTemplate.postForObject(getRootUrl() + "/api/v2/schemas",dbUser, String[].class);
		Assert.assertTrue(schemaList.length > 0);
	}

	/**
	 * Tests get columns in a table
	 */
	@Test
	public void testGetColumns() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		String[] tableColumns = restTemplate.postForObject(getRootUrl() + "/api/v2/columns/"+TABLE_NAME, dbUser, String[].class);
		Assert.assertTrue(tableColumns.length > 0);
	}

	/**
	 * Test Data preview of the table
	 */
	@Test
	public void testGetTableData() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		TableData[] tableDatas = restTemplate.postForObject(getRootUrl() + "/api/v2/data/"+TABLE_NAME,dbUser, TableData[].class);
		Assert.assertTrue(tableDatas.length > 0);
	}


	/**
	 * Test for getting the table statistics
	 */
	@Test
	public void testGetTableStatistics() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		TableStatistics tableStatistics = restTemplate.postForObject(getRootUrl() + "/api/v3/statistics/table/"+TABLE_NAME, dbUser, TableStatistics.class);
		Assert.assertTrue(Long.parseLong(tableStatistics.getRecordCount()) > 0);
		Assert.assertTrue(tableStatistics.getAttributeCount() > 0);
	}
	
	/**
	 * Test for getting column statistics
	 */
	@Test
	public void testGetColumnStatistics() {
		DbUser dbUser = new DbUser();
		dbUser.setDatabaseName("taskdb");
		dbUser.setUserName("postgres");
		dbUser.setPassword("test@1234");
		ColumnStatistics columnStatistics = restTemplate.postForObject(getRootUrl() + "/api/v3/statistics/"+TABLE_NAME+"/"+COLUMN_NAME_ID, dbUser, ColumnStatistics.class);
		Assert.assertTrue(!columnStatistics.getAvgValue().isEmpty());
		Assert.assertTrue(!columnStatistics.getMaxValue().isEmpty());
		Assert.assertTrue(!columnStatistics.getMinValue().isEmpty());
		Assert.assertTrue(!columnStatistics.getMedianValue().isEmpty());
	}
	
	

}
