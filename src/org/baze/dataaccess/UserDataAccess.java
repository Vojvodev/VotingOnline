package org.baze.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.baze.model.User;



public class UserDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ALL 						= "SELECT * FROM users";
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username=? AND password=?";
	private static final String SQL_SELECT_BY_ID 			  		= "SELECT * FROM users WHERE id=?";
	private static final String SQL_INSERT 					  		= "INSERT INTO users (fname, lname, username, password, email, address) VALUES (?, ?, ?, ?, ? , ?)";
	private static final String SQL_UPDATE 					  		= "UPDATE users SET fname=?,lname=?,username=?,password=?,email=?,address=? WHERE id=?";
	private static final String SQL_DELETE					 		= "DELETE FROM users WHERE id=?";
	private static final String SQL_UPDATE_VOTED 					= "UPDATE users SET voted=? WHERE id=?";
	private static final String SQL_COUNT_USERS 					= "CALL count_users()";
	
	private static final String SQL_GET_NOTIFICATION 				= "SELECT content FROM notifications "
																	+ "JOIN users_has_notifications ON notifications_id=id "
																	+ "WHERE users_id=?";
	
	
	public static ArrayList<User> selectAllUsers(){
		ArrayList<User> users = new ArrayList<User>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				users.add(new User(
												rs.getInt("id"), 
												rs.getString("fname"), 
												rs.getString("lname"),
												rs.getString("username"),
												rs.getString("password"),
												rs.getString("email"),
												rs.getBoolean("voted"),
												rs.getBoolean("admin"),
												rs.getString("address"),
												rs.getInt("voting_locations_id"), 
												rs.getString("created_at")
											));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	
	
	public static String getNotificationbyId(int id){
		String notification = "";
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_GET_NOTIFICATION, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				notification += rs.getString("content") + "\n";
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return notification;
	}
	
	
	
	
	public static int getNumberOfUsers(){
		int numberOfUsers = 0;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_COUNT_USERS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				numberOfUsers = rs.getInt("user_count");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return numberOfUsers;
	}
	
	
	
	
	public static User selectByUsernameAndPassword(String username, String password){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(
									rs.getInt("id"), 
									rs.getString("fname"), 
									rs.getString("lname"), 
									rs.getString("username"), 
									rs.getString("password"),
									rs.getString("email"),
									rs.getBoolean("voted"),
									rs.getBoolean("admin"),
									rs.getString("address"),
									rs.getInt("voting_locations_id"), 
									rs.getString("created_at") 
								);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	
	public static User getById(int id) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User(
						rs.getInt("id"), 
						rs.getString("fname"), 
						rs.getString("lname"), 
						rs.getString("username"), 
						rs.getString("password"),
						rs.getString("email"),
						rs.getBoolean("voted"),
						rs.getBoolean("admin"),
						rs.getString("address"),
						rs.getInt("voting_locations_id"), 
						rs.getString("created_at") 
					);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}


	public static boolean create(String fname, String lname, String username, String password, String email, String address) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { fname, lname, username, password, email, address };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_INSERT, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	
	public static boolean update(int userId, String fname, String lname, String username, String password, String email, String address){
		Connection connection = null;
		boolean result = false;
		Object values[] = { fname, lname, username, password, email, address, userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_UPDATE, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	
	public static boolean delete(int id){
		Connection connection = null;
		boolean result = false;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_DELETE, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}

	
	
	public static boolean setVoted(int userId, boolean voted){
		Connection connection = null;
		boolean result = false;
		Object values[] = { voted, userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_UPDATE_VOTED, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}

}
