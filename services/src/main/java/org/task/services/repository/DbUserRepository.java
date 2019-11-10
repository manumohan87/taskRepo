package org.task.services.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.task.services.model.DbUser;

/**
 * Task1 : The repository interface which communicates with database for saving, updating, listing and deleting connection details to database
 * @author Manu Mohan
 *
 */
@Repository
public class DbUserRepository implements IDbUserRepository {



	@Autowired
	private JdbcTemplate template;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean createUser(DbUser user) {
		String createUserQuery = "CREATE USER "+ user.getUserName() +" WITH PASSWORD '" +user.getPassword()+"'";
		template.execute(createUserQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean createDataBaseForUser(DbUser user) {
		String createDbQuery = "CREATE DATABASE "+ user.getDatabaseName() + " OWNER " + user.getUserName();
		template.execute(createDbQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean updateUserName(String oldName, String newName) {
		String alterUserQuery =  "ALTER USER "+oldName+" RENAME TO "+newName;
		template.execute(alterUserQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean updateUserPassword(String userName, String newPassword) {
		String alterUserPasswordQuery = "ALTER USER "+userName +" WITH PASSWORD '"+ newPassword +"'";
		template.execute(alterUserPasswordQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean deleteDatabase(String dbName) {
		String dropDBQuery = "DROP DATABASE "+dbName;
		template.execute(dropDBQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean deleteUser(String userName) {
		String dropUserQuery = "DROP USER "+userName;
		template.execute(dropUserQuery);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DbUser> getAllUsers() {
		List<DbUser> dbUsers = template.query("select usename as username, passwd as password_md5 from pg_shadow order by usename;", new RowMapper<DbUser>() {

			@Override
			public DbUser mapRow(ResultSet rs, int rowNum) throws SQLException {

				DbUser dbUser = new DbUser();
				dbUser.setUserName(rs.getString(1));
				dbUser.setPassword(rs.getString(2));
				dbUser.setDatabases(getDatabasesOwnedByUser(rs.getString(1)));
				return dbUser;
			}
		});


		return dbUsers;
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbUser getUserByName(String userName) {


		DbUser dbUser = template.queryForObject("select usename, passwd from pg_shadow where usename = '"+userName+"'", new RowMapper<DbUser>() {

			@Override
			public DbUser mapRow(ResultSet rs, int rowNum) throws SQLException {

				DbUser dbUser = new DbUser();
				dbUser.setUserName(rs.getString(1));
				dbUser.setDatabases(getDatabasesOwnedByUser(userName));
				dbUser.setPassword(rs.getString(2));
				return dbUser;
			}

		});

		return dbUser;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getDatabasesOwnedByUser(String userName) {
		List<String> dbUsers = template.query("select datname FROM pg_database pg JOIN pg_authid pgid  on pg.datdba = pgid.oid where rolname = '"+userName+"'", new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		});
		return dbUsers;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean renameDataBase(String oldDbName, String newName) {
		String renameQuery = "ALTER DATABASE "+oldDbName+" RENAME TO "+newName;
		template.execute(renameQuery);
		return true;
	}

}

