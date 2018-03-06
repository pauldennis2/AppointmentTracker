package com.pauldennis.AppointmentTracker;

public class Appointment {
	
	private String date;
	private String time;
	private String description;
	
	public Appointment(String date, String time, String text) {
		this.date = date;
		this.time = time;
		this.description = text;
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
	public String getText() {
		return description;
	}
	public void setText(String text) {
		this.description = text;
	}
}
