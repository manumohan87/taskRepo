package org.task.services.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.services.model.DbUser;
import org.task.services.repository.DbUserRepository;

/**
 * Task 1:
 * The controller class which defines REST APi's for saving, updating, listing and deleting connection details to database
 * 
 * @author Manu Mohan
 *
 */
@RestController
@RequestMapping("/api/v1")
public class DbUserController {

	@Autowired
	private DbUserRepository userRepository;
	

	
	/**
	 * Post request to create an user
	 * @param user {@link DbUser} which contains information about the user to be created.
	 * @return Map with created status true
	 * @return {@link DbUser} Created user information
	 */
	@PostMapping("/users")
	public ResponseEntity<Map<String, Boolean>> createUser(@Valid @RequestBody DbUser user) {
		
		userRepository.createUser(user);
		userRepository.createDataBaseForUser(user);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("created", Boolean.TRUE);
		
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Put request to update a user details.
	 * @param userName the name of user.
	 * @param userDetails {@link DbUser} which contains new user information to be updated
	 * @return with created status true
	 * @return {@link DbUser} Updated user information
	 * 
	 */
	@PutMapping("/users/{name}")
	public ResponseEntity<Map<String, Boolean>> updateUser(@PathVariable(value = "name") String userName,  @Valid @RequestBody DbUser userDetails) {
		
		userRepository.updateUserName(userName, userDetails.getUserName());
		
		if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
			userRepository.updateUserPassword(userDetails.getUserName(), userDetails.getPassword());
		}
	
		Map<String, Boolean> response = new HashMap<>();
		response.put("updated", Boolean.TRUE);
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Deletes the user with the name.
	 * @param userName the name of the user
	 * @return Map with deleted status true
	 * 
	 */
	@DeleteMapping("/deletuser/{userName}")
	public ResponseEntity<Map<String, Boolean>> deleteUser( @PathVariable(value = "userName") String userName) {
		
		
		List<String> databases = userRepository.getDatabasesOwnedByUser(userName);
		
		if(!databases.isEmpty()) {
			for (String dbName : databases) {
				userRepository.deleteDatabase(dbName);
			}
		}
		userRepository.deleteUser(userName);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Gets all the DBusers information. 
	 * @return list of {@link DbUser} list of user information
	 */
	@GetMapping("/listusers")
	public ResponseEntity<List<DbUser>> listUsers() {
		List<DbUser> allUsers = userRepository.getAllUsers();
		return ResponseEntity.ok().body(allUsers);
	}
	
	/**
	 * Gets the user details with name
	 * @param userName userName
	 * @return {@link DbUser} Dbuser information
	 */
	@GetMapping("/listusers/{name}")
	public ResponseEntity<DbUser> getUserByName(@PathVariable(value = "name") String userName){
		DbUser user = userRepository.getUserByName(userName);
		return ResponseEntity.ok().body(user);
	}
	
	/**
	 * Gets the database owned by the user
	 * @param userName name of the user
	 * @return list of databases owned by the user.
	 */
	@GetMapping("/database/{name}")
	public ResponseEntity<List<String>> getDabasesOwnedByUser(@PathVariable(value = "name") String userName){
		List<String> databases = userRepository.getDatabasesOwnedByUser(userName);
		return ResponseEntity.ok().body(databases);
	}
	
	@PutMapping("/database/{oldDbName}/{newdDbName}")
	public ResponseEntity<Map<String, Boolean>> renameDatabaseName(@PathVariable(value = "oldDbName") String oldDbName, @PathVariable(value = "newdDbName") String newdDbName){
		userRepository.renameDataBase(oldDbName, newdDbName);
		Map<String, Boolean> response = new HashMap<>();
		response.put("renamed", Boolean.TRUE);
		return ResponseEntity.ok().body(response);
	}
	
	
}
