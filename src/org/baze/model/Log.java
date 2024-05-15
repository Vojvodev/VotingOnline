package org.baze.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Log implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int users_id;
	private String action;
	private Timestamp time;
	
	
	
	
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(int id, int users_id, String action, Timestamp time) {
		super();
		this.id = id;
		this.users_id = users_id;
		this.action = action;
		this.time = time;
	}
	
	public Log(int users_id, String action) {
		super();
		this.users_id = users_id;
		this.action = action;
	}
	
	public Log(int id, int users_id, String action, String time) {
		super();
		this.id = id;
		this.users_id = users_id;
		this.action = action;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
            java.util.Date parsedDate = dateFormat.parse(time);
            this.time = new Timestamp(parsedDate.getTime());
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

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
}
