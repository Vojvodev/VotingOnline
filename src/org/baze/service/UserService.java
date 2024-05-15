package org.baze.service;

import org.baze.dataaccess.UserDataAccess;
import org.baze.model.User;


public class UserService {
	
	
	public User loginUser(String username, String password){
		if(username == null || password == null
				|| username == "" || password == ""){
			return null;
		}

		return UserDataAccess.selectByUsernameAndPassword(username, password);
	}
	
	
	public User selectByUsernameAndPassword(String username, String password) {
		return UserDataAccess.selectByUsernameAndPassword(username, password);	
	}
	
	public User selectById(int id) {
		return UserDataAccess.getById(id);	
	}
	
	public boolean deleteUser(int id) {
		return UserDataAccess.delete(id);	
	}
	
	
	public boolean createUser(String fname, String lname, String username, String password, String email, String address) {
		return UserDataAccess.create(fname, lname, username, password, email, address);
	}
	
	public boolean updateUser(int id, String fname, String lname, String username, String password, String email, String address) {
		return UserDataAccess.update(id, fname, lname, username, password, email, address);
	}
	
	
	
}
