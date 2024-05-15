package org.baze.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Vote implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int candidatesId;
	private int usersId;
	private int electionsId;
	private Timestamp createdAt;
	
	
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vote(int id, int candidatesId, int usersId, int electionsId, Timestamp createdAt) {
		super();
		this.id = id;
		this.candidatesId = candidatesId;
		this.usersId = usersId;
		this.electionsId = electionsId;
		this.createdAt = createdAt;
	}
	
	public Vote(int id, int candidatesId, int usersId, int electionsId, String createdAt) {
		super();
		this.id = id;
		this.candidatesId = candidatesId;
		this.usersId = usersId;
		this.electionsId = electionsId;
		
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
	public int getCandidatesId() {
		return candidatesId;
	}
	public void setCandidatesId(int candidatesId) {
		this.candidatesId = candidatesId;
	}
	public int getUsersId() {
		return usersId;
	}
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	public int getElectionsId() {
		return electionsId;
	}
	public void setElectionsId(int electionsId) {
		this.electionsId = electionsId;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
