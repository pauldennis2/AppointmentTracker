package com.pauldennis.AppointmentTracker;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppointmentDatabaseTest {
	
	AppointmentDatabase appointmentDb;
	
	Appointment apt1;
	Appointment apt2;

	@Before
	public void setUp() throws SQLException {
		appointmentDb = new AppointmentDatabase();
		appointmentDb.init();
		
		apt1 = new Appointment ("3/7/2018", "10:55", "unit test the db");
		apt2 = new Appointment ("3/7/2018", "11:05", "More unit testing");
		
		appointmentDb.removeAppointmentsByName("unit test");
	}

	@After
	public void tearDown() throws SQLException {
		System.out.println("Running tearDown()");
		appointmentDb.removeAppointmentsByName("unit test");
	}

	@Test
	public void testAddingRecords() throws SQLException {
		//Both apt1 and apt2 contain this string but we want to make sure
		//There aren't any pre-existing appointments with this string
		int size = appointmentDb.getAppointmentsByName("unit test").size();
		assertEquals(0, size);
		appointmentDb.addAppointment(apt1);
		appointmentDb.addAppointment(apt2);
		
		size = appointmentDb.getAppointmentsByName("unit test").size();
		assertEquals(2, size);
		
	}

}
