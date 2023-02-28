package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Appointment;
import com.example.demo.services.AppointmentsService;
import com.example.demo.services.UserService;

@RestController 
@RequestMapping("/appointments")
@CrossOrigin 
public class AppointmentsController {
	
	private final AppointmentsService service;
	public	AppointmentsController(AppointmentsService service) {
		this.service = service;		
	}
	
	@PostMapping
	public void addNewAppointment(@RequestBody Appointment appointment) {
		try {
			service.addNewAppointment(appointment);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);

		}
	}
	
	//get appointments by patientId and doctorId
	@GetMapping
	public Iterable<Appointment> getAppointments(@RequestParam(required = false) Long patientId,
			     								@RequestParam(required = false) Long doctorId){
		return service.getAppointments(patientId, doctorId);
	}
	
	@DeleteMapping("/{id}") 
	public void deleteById(@PathVariable Long id) {
		try {
			service.deleteById(id);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public void modifyAppointment(@RequestBody  Long id, Appointment appointment) {
		try {
			service.modifyAppointment(id, appointment);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//3 different way to pass information from FE to the BE
	// @PathVariable , URL variable ( when we use @RequestParm) and as a Body ( @RequestBody)

}
