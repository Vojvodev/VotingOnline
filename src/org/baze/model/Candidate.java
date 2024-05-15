package org.baze.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.baze.dataaccess.CandidateDataAccess;

public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fname;
	private String lname;
	private int partiesId;
	private Timestamp createdAt;
	
	
	
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidate(int id, String fname, String lname, int partiesId, Timestamp createdAt) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.partiesId = partiesId;
		this.createdAt = createdAt;
	}
	public Candidate(int id, String fname, String lname, int partiesId, String createdAt) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.partiesId = partiesId;
				
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
	public int getPartiesId() {
		return partiesId;
	}
	public void setPartiesId(int partiesId) {
		this.partiesId = partiesId;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public String getPartyName() {
		return CandidateDataAccess.getPartyNameById(this.partiesId);
	}
	
	public String getPartyAbout() {
		return CandidateDataAccess.getPartyAboutById(this.partiesId);
	}
	
	
}