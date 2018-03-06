package com.pauldennis.AppointmentTracker;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentTrackerController {
	
	AppointmentDatabase appointmentDatabase;
	
	@RequestMapping (path = "/", method = RequestMethod.GET)
	public String home (Model model) throws SQLException {
		if (appointmentDatabase == null) {
			appointmentDatabase = new AppointmentDatabase();
			appointmentDatabase.init();
		}
		List<Appointment> appointments = appointmentDatabase.getAllAppointments();
		model.addAttribute("appointments", appointments);
		
		System.out.println("At the home endpoint");
		return "home";
	}
	
	@RequestMapping (path = "/add-appointment", method = RequestMethod.POST)
	public String addAppointment (String date, String time, String description) throws SQLException {
		
		if (appointmentDatabase == null) {
			appointmentDatabase = new AppointmentDatabase();
			appointmentDatabase.init();
		}
		
		Appointment newApt = new Appointment (date, time, description);
		System.out.println("Attempting to add new appointment:");
		System.out.println(newApt);
		appointmentDatabase.addAppointment(newApt);
		return "redirect:/";
	}
	
	@RequestMapping (path = "/search", method = RequestMethod.GET)
	public String searchAppointments (Model model, String searchText) throws SQLException {
		List<Appointment> appointments = appointmentDatabase.getAppointmentsByName(searchText);
		model.addAttribute("appointments", appointments);
		return "home";
	}
}
