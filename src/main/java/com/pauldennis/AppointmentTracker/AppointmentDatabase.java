package com.pauldennis.AppointmentTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.tools.Server;

public class AppointmentDatabase {
	
	public final static String DB_URL = "jdbc:h2:./main";
	
	private Connection conn;
	
	public void init() throws SQLException {
        Server.createWebServer().start();
        //DB username is "sa", password blank
        conn = DriverManager.getConnection(DB_URL, "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS appointments (date VARCHAR, time VARCHAR, description VARCHAR)");
    }
	
	public void addAppointment(Appointment appointment) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments VALUES (?, ?, ?);");
        stmt.setString(1, appointment.getDate());
        stmt.setString(2, appointment.getTime());
        stmt.setString(3, appointment.getDescription());
        stmt.execute();
    }
	
	public List<Appointment> getAllAppointments () throws SQLException {
		List<Appointment> appointments = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM appointments");
        while (results.next()) {
            String date = results.getString("date");
            String time = results.getString("time");
            String description = results.getString("description");
            appointments.add(new Appointment(date, time, description));
        }
        return appointments;
	}
	
	public List<Appointment> getAppointmentsByName (String name) throws SQLException {
		System.out.println("Searching for appointments with name/description of: " + name);
		//Whee! Java 8 streams
		return getAllAppointments().stream()
			.filter(appointment -> appointment.getDescription().contains(name))
			.collect(Collectors.toList());
	}
	
	//This method will remove all appointments that CONTAIN the given name
	//I.e. same as "search" functionality
	public void removeAppointmentsByName (String name) throws SQLException {
		List<Appointment> appointmentsForDeletion = getAppointmentsByName(name);
		
		System.out.println("In removeAppointmentsByName(). Found:");
		appointmentsForDeletion.forEach(System.out::println);
		
		//Pretty inefficient to do multiple queries
		appointmentsForDeletion.forEach(appointment -> {
			try {
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM appointments WHERE description = ?");
				stmt.setString(1, appointment.getDescription());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
}
