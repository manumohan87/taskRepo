package org.task.services.model;

import java.util.List;

/**
 * Task 1: 
 * The model class to represent DB user
 * @author Manu Mohan
 *
 */
public class DbUser {

	private String databaseName;
	private String userName;
	private String password;
	private List<String> databases;

	
	/**
	 * Gets the database name to be connected
	 * @return the database name
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	
	/**
	 * Sets the database name to be connected
	 * @param databaseName
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * Gets the username for connecting to the database
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the username for connecting to the database
	 * @param userName the username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password for connecting to the database
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password for connecting to the database
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the databases
	 * @return the list of database names owned by the user
	 */
	public List<String> getDatabases() {
		return databases;
	}
	
	/**
	 * Returns the list of databases owned by user
	 * @param databases
	 */
	public void setDatabases(List<String> databases) {
		this.databases = databases;
	}
	

}
