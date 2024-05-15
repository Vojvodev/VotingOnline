package org.baze.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.baze.model.Log;


public class LogsDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ALL  = "SELECT * FROM logs";
	private static final String SQL_INSERT 		= "INSERT INTO logs (users_id, action) VALUES (?, ?)";
	
	
	
	public static boolean createLog(Log log) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { log.getUsers_id(), log.getAction() };
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
	
	
	public static ArrayList<Log> getLogs(){
		ArrayList<Log> logs = new ArrayList<Log>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				logs.add(new Log(
												rs.getInt("id"), 
												rs.getInt("users_id"), 
												rs.getString("action"), 
												rs.getString("created_at")
											));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return logs;
	}
	
	
}
