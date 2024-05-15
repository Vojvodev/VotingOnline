package org.baze.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Election implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Date year;
	private Date startDate;
	private Date endDate;
	private String type;
	private boolean active;
	
	
	public Election() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Election(int id, String name, Date year, Date startDate, Date endDate, String type, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.setActive(active);
	}
	
	public Election(int id, String name, String year, String startDate, String endDate, String type, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.setActive(active);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
            Date parsedYear  = new Date(dateFormat.parse(year).getTime());
            Date parsedStart = new Date(dateFormat.parse(startDate).getTime());
            Date parsedEnd 	 = new Date(dateFormat.parse(endDate).getTime());
            
            this.year 		= new Date(parsedYear.getTime());
            this.startDate	= new Date(parsedStart.getTime());
            this.endDate	= new Date(parsedEnd.getTime());
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
