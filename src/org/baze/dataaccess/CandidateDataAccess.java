package org.baze.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.baze.model.Candidate;



public class CandidateDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ALL 				  = "SELECT * FROM candidates";
	private static final String SQL_SELECT_BY_ID 			  = "SELECT * FROM candidates WHERE id=?";
	private static final String SQL_SELECT_BY_FNAME_AND_LNAME = "SELECT * FROM candidates WHERE fname=? AND lname=?";
	private static final String SQL_SEARCH_BY_NAME			  = "SELECT * FROM candidates WHERE fname LIKE CONCAT('%', ?, '%') ";
	private static final String SQL_INSERT 					  = "INSERT INTO candidates (fname, lname, parties_id) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE 					  = "UPDATE candidates SET fname=?,lname=?,parties_id=? WHERE id=?";
	private static final String SQL_DELETE					  = "DELETE FROM candidates WHERE id=?";
	
	private static final String SQL_GET_PARTY_NAME_BY_ID	  = "SELECT * FROM parties WHERE id=?";
	private static final String SQL_GET_PARTY_ABOUT_BY_ID	  = "SELECT * FROM parties WHERE id=?";
	private static final String SQL_GET_SLOGAN	 			  = "SELECT * FROM campaigns WHERE parties_id=?";

	
	
	public static String getSlogan(int partiesId) {
		String slogan = "";
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { partiesId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_GET_SLOGAN, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				slogan = rs.getString("slogan");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return slogan;
	}
	
	
	public static ArrayList<Candidate> selectAllCandidates(){
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				candidates.add(new Candidate(
												rs.getInt("id"), 
												rs.getString("fname"), 
												rs.getString("lname"), 
												rs.getInt("parties_id"), 
												rs.getString("created_at")
											));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return candidates;
	}
	
	
	public static Candidate getById(int id) {
		Candidate candidate = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				candidate = new Candidate(
												rs.getInt("id"), 
												rs.getString("fname"), 
												rs.getString("lname"), 
												rs.getInt("parties_id"), 
												rs.getString("created_at")
											);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return candidate;
	}
	
	
	
	public static Candidate getByFnameAndLname(String fname, String lname) {
		Candidate candidate = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {fname, lname};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_FNAME_AND_LNAME, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				candidate = new Candidate(
												rs.getInt("id"), 
												rs.getString("fname"), 
												rs.getString("lname"), 
												rs.getInt("parties_id"), 
												rs.getString("created_at")
											);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return candidate;
	}
	
	
	public static Candidate searchByName(String name) {
		Candidate candidate = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { name };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SEARCH_BY_NAME, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				candidate = new Candidate(
												rs.getInt("id"), 
												rs.getString("fname"), 
												rs.getString("lname"), 
												rs.getInt("parties_id"), 
												rs.getString("created_at")
											);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return candidate;
	}
	
	
	
	public static boolean create(String fname, String lname, int partyId) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { fname, lname, partyId};
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
	
	
	public static boolean update(int candidateId, String fname, String lname, int partyId){
		Connection connection = null;
		boolean result = false;
		Object values[] = { fname, lname, partyId, candidateId };
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
	
	
	public static String getPartyNameById(int id) {
		String name = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_GET_PARTY_NAME_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return name;
	}
	
	public static String getPartyAboutById(int id) {
		String about = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_GET_PARTY_ABOUT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if(rs.next()){
				about = rs.getString("about");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return about;
	}
	
}
