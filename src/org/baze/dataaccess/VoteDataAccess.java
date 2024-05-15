package org.baze.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.baze.model.Candidate;
import org.baze.model.Vote;


public class VoteDataAccess {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT 						= "INSERT INTO votes (users_id, candidates_id, elections_id) VALUES (?, ?, ?)";
	private static final String SQL_SELECT_BY_USER_AND_ELECTION = "SELECT * FROM votes WHERE users_id=? AND elections_id=?";
	private static final String SQL_GET_WINNER 					= "CALL calculate_winner(?)";
	private static final String SQL_COUNT_VOTES 				= "CALL count_votes(?)";
	
	
	public static boolean createVote(int userId, int candidateId, int electionId) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { userId, candidateId, electionId };
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
	
	
	public static Vote selectByUserAndElection(int userId, int electionId) {
		Vote vote = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {userId, electionId};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_SELECT_BY_USER_AND_ELECTION, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				vote = new Vote(
									rs.getInt("id"),
									rs.getInt("candidates_id"),
									rs.getInt("users_id"),
									rs.getInt("elections_id"),
									rs.getString("created_at")
								);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return vote;
	}
	
	
	public static Candidate getWinner(int electionsId) {
		Candidate candidate = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { electionsId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_GET_WINNER, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				candidate = CandidateDataAccess.getById(rs.getInt("candidates_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return candidate;
	}
	
	
	public static int getNumberOfVotes(int electionId) {
		int numberOfVotes = 0;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { electionId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = Util.prepareStatement(connection, SQL_COUNT_VOTES, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				numberOfVotes = rs.getInt("vote_count");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return numberOfVotes;
	}
	
	
}
