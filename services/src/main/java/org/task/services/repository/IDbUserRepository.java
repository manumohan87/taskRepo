package org.task.services.repository;

import java.util.List;

import org.task.services.model.DbUser;

/**
 * Task 1: The repository interface which declares methods to for saving, updating, listing and deleting connection details to database
 * @author Manu Mohan
 *
 */
public interface IDbUserRepository {
		
	/**
	 * Creates a user with user name and password 
	 * @param user {@link DbUser} connection details
	 * @return true if created 
	 */
	public Boolean createUser(DbUser user);
	
	/**
	 * Creates a database for the user
	 * @param user
	 * @return
	 */
	public Boolean createDataBaseForUser(DbUser user);
	
	/**
	 * Update user name 
	 * @param oldName old user name
	 * @param newName new user name
	 * @return true if user information updated.
	 */
	public Boolean updateUserName(String oldName, String newName);
	
	/**
	 * Updates user password
	 * @param userName user name
	 * @param newPassword user password
	 *   
	 * @return true if user information updated
	 */
	public Boolean updateUserPassword(String userName, String newPassword);
	
	/**
	 * Delete data base
	 * @param dbName name of database
	 * @return true if deleted
	 */
	public Boolean deleteDatabase(String dbName);
	
	/**
	 * Delete the user
	 * @param dbUser name of the user
	 * @return true if deleted
	 */
	public Boolean deleteUser(String dbUser);
	
	/**
	 * Gets all the users 
	 * @return {@link DbUser} list of dbusers
	 */
	public List<DbUser> getAllUsers();
	
	/**
	 * Gets the user by name
	 * @param userName name of the user
	 * @return {@link DbUser} connection details of user
	 */
	public DbUser getUserByName(String userName);
	
	/**
	 * Gets the databases owned by the user
	 * @param userName
	 * @return list if database names owned by the user
	 */
	public List<String> getDatabasesOwnedByUser(String userName);
	
	/**
	 * Renames the database name 
	 * @param oldName old database name
	 * @param newName new database name
	 * @return true if renamed 
	 */
	public Boolean renameDataBase(String oldName, String newName);
	
}
