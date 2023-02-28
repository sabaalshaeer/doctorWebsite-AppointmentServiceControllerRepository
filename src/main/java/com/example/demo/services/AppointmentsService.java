package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Appointment;

import repositories.AppointmentRepository;

@Service
public class AppointmentsService {

	
	private Long nextAppointmentId = 0L;
	
	//we going to save the appointment in the database so we dont need this array anymore
	//	private ArrayList<Appointment> appointments = new ArrayList<>();
	//inject repository
   private final AppointmentRepository appointmentRepository;
	//create constructor
	public AppointmentsService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	//if you as a Dev, know that this method can throw, then java wants you to declare that up front
	public void addNewAppointment( Appointment appointment) {
//		for(Appointment existingAppointment : appointments) {
//			if (existingAppointment.id.equals(appointment.id))
//				throw new Exception();
//		}
//		appointment.id = nextAppointmentId++;
//		appointments.add(appointment);
		
		
		//take your entity and save by repository into the database
		this.appointmentRepository.save(appointment);
	}
	
	public Iterable<Appointment> getAppointments( Long patientId){
//		if(patientId != null) {
//			final var matching = new ArrayList<Appointment>();
//			for (Appointment appointment : appointments)
//				if(patientId.equals(appointment.patientId)) {
//					matching.add(appointment);
//				}
//			return matching;
//		}
		
		
		if(patientId != null) {
			//find appointment with matching patientId
			
			//we can say:
			//return this.appointmentRepository.findAllByPatientId(patientId);
			
			//or this:
			Iterable<Appointment> appointmentList = this.appointmentRepository.findAllByPatientId(patientId);
			//this function will go to the database , will filter the appointment according to this provided patientId and return them then store these appointments in a variable
			//now appointmentList will be equal to matching 
			return appointmentList;
		}
		
		
//		we dont pass doctorIdso we dont need these lines
//		if(doctorId != null) {
//			final var matching = new ArrayList<Appointment>();
//			for (Appointment appointment : appointments)
//				if(doctorId.equals(appointment.doctorId)) {
//					matching.add(appointment);
//				}
//			return matching;
//		}

//		return appointments;
		return this.appointmentRepository.findAll();
	}
	
	public void deleteById( Long id) throws Exception {
//		for( var appointment: appointments) {
//			if(appointment.id.equals(id)) {
//				appointments.remove(appointment);
//			return;
//			}
//		}
		
		//check if the id is exist:
		Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(id);
		if(appointmentOpt.isEmpty()) {
			//the id is not in the database
			throw new Exception();
		}
		//if we Here then the id is exist in the database
		this.appointmentRepository.deleteById(id);
	}
	
	public void modifyAppointment(Long id, Appointment updatedAppointment) throws Exception {
		//		if(!id.equals(appointment.id))
		//		 throw new Exception();
		//check if the id is exist:
				Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(id);
				if(appointmentOpt.isEmpty()) {
					//the id is not in the database
					throw new Exception();
				}					
//		for(var existingAppointment: appointments) {
//			if(id.equals(existingAppointment.id)) {
//				appointments.remove(existingAppointment);
//				appointments.add(appointment);
//				return;
//			}
//		}
				//get the appointment from the database, we have the entity from the database
				Appointment appointment = appointmentOpt.get();
				//set the appointmentRequest, we are replacing that entity values
				appointment.date = updatedAppointment.date;
				appointment.patientId = updatedAppointment.patientId;
				appointment.slot = updatedAppointment.slot;
				//don`t update id because you will create new row instead of update the specific appointment with specific id
				
				appointmentRepository.save(appointment);

	}
	
	
	
	//there is 2 types of exceptions in Java:
	//1.where you are effectively , you need to communicate that whoever`s alling you needs to handle this exception(I can say standard exception)
	//2.where you are really not excepting this to happen (you can communicate with RunTimeException)
	


}
