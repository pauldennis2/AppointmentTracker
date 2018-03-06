package com.pauldennis.AppointmentTracker;

public class Appointment {
	
	private Integer id;
	private String date;
	private String time;
	private String description;
	
	public Appointment(String date, String time, String text) {
		this.date = date;
		this.time = time;
		this.description = text;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
