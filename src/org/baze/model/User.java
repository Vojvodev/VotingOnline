package org.baze.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String email;
	private boolean voted;
	private boolean admin;
	private String address;
	private int votingLocationsId;
	private Timestamp createdAt;
	
	private boolean loggedIn = false;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int id, String fname, String lname, String username, String password, String email, boolean voted,
			boolean admin, String address, int votingLocationsId, Timestamp createdAt) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.voted = voted;
		this.admin = admin;
		this.address = address;
		this.votingLocationsId = votingLocationsId;
		this.createdAt = createdAt;
	}

	
	public User(int id, String fname, String lname, String username, String password, String email, boolean voted,
			boolean admin, String address, int votingLocationsId, String createdAt) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.voted = voted;
		this.admin = admin;
		this.address = address;
		this.votingLocationsId = votingLocationsId;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
            java.util.Date parsedDate = dateFormat.parse(createdAt);
            this.createdAt = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isVoted() {
		return voted;
	}


	public void setVoted(boolean voted) {
		this.voted = voted;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getVotingLocationsId() {
		return votingLocationsId;
	}


	public void setVotingLocationsId(int votingLocationsId) {
		this.votingLocationsId = votingLocationsId;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
}