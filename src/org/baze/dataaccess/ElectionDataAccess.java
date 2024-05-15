package org.baze.dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.baze.model.Election;


public class ElectionDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ACTIVE 			= "SELECT * FROM elections WHERE active=1";
	private static final String SQL_SELECT_ALL 	  			= "SELECT * FROM elections";
	private static final String SQL_SELECT_BY_ID 			= "SELECT * FROM elections WHERE id=?";
	private static final String SQL_SELECT_ACTIVE_BY_NAME 	= "SELECT * FROM elections WHERE name=?";
	private static final String SQL_INSERT 					= "INSERT INTO elections (name, type, year, start_date, end_date, active) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE 					= "UPDATE elections SET name=?,type=?,year=?,start_date=?,end_date=?,active=? WHERE id=?";
	private static final String SQL_DELETE					= "DELETE FROM elections WHERE id=?";
	
	
	
	public static ArrayList<Election> selectAllElections(){
		ArrayList<Election> elections = new ArrayList<Election>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				elections.add(new Election(
											rs.getInt("id"), 
											rs.getString("name"), 
											rs.getString("year"), 
											rs.getString("start_date"),
											rs.getString("end_date"),
											rs.getString("type"),
											rs.getBoolean("active") 
										  ));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return elections;
	}
	
	
	public static ArrayList<Election> selectActiveElections(){
		ArrayList<Election> elections = new ArrayList<Election>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ACTIVE, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				elections.add(new Election(
											rs.getInt("id"), 
											rs.getString("name"), 
											rs.getString("year"), 
											rs.getString("start_date"),
											rs.getString("end_date"),
											rs.getString("type"),
											rs.getBoolean("active") 
										  ));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return elections;
	}
	
	
	public static Election getById(int id) {
		Election election = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				election = new Election(
										rs.getInt("id"), 
										rs.getString("name"), 
										rs.getString("year"), 
										rs.getString("start_date"),
										rs.getString("end_date"),
										rs.getString("type"),
										rs.getBoolean("active") 
										);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return election;
	}
	
	
	
	public static Election getActiveByName(String name) {
		Election election = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { name };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ACTIVE_BY_NAME, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				election = new Election(
										rs.getInt("id"), 
										rs.getString("name"), 
										rs.getString("year"), 
										rs.getString("start_date"),
										rs.getString("end_date"),
										rs.getString("type"),
										rs.getBoolean("active") 
									  );
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return election;
	}
	
	
	public static boolean create(String name, String type, Date year, Date start, Date end, boolean active) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { name, type, year, start, end, active };
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
	
	
	public static boolean update(int id, String name, String type, Date year, Date start, Date end, boolean active){
		Connection connection = null;
		boolean result = false;
		Object values[] = { name, type, year, start, end, active, id };
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
}
