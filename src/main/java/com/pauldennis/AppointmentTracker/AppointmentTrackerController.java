package com.pauldennis.AppointmentTracker;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentTrackerController {
	
	@RequestMapping (path = "/", method = RequestMethod.GET)
	public String home (HttpSession session, Model model) {
		//Temporary: storing the appointments in the session instead of the db
		List<Appointment> appointments = (List<Appointment>) session.getAttribute("appointments");
		model.addAttribute("appointments", appointments);
		System.out.println("At the home endpoint");
		return "home";
	}
	
	@RequestMapping (path = "/add-appointment", method = RequestMethod.POST)
	public String addAppointment () {
		return "home";
	}
}
