package org.baze.dataaccess;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FeedbackDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO users_feedback (content) VALUES (?)";
	
	
	public static boolean create(String content) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { content };
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
	
	
	public static void reportProblem() {
		try {
			String encodedRecipient = java.net.URLEncoder.encode("report@voting.com", "UTF-8");
            String encodedSubject = java.net.URLEncoder.encode("PROBLEM_REPORT", "UTF-8");

            URI mailtoURI = new URI("mailto:" + encodedRecipient + "?subject=" + encodedSubject);

            Desktop.getDesktop().mail(mailtoURI);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
}
