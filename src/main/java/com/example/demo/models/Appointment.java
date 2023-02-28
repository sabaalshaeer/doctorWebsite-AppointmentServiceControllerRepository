package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//definition of Appointment

//The JPA specification defines a set of standard annotations, such as @Entity, @Id, @GeneratedValue, etc.,
//which are used to map Java objects to database tables and define the relationships between them.
@Entity
public class Appointment {
	@Id //specific the id as primary key
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	public Long id;
  //public Long doctorId;
	public Long patientId;
	public Date date;
	public int slot;
	
	public Appointment(Long id, Long patientId, Date date, int slot) {
		this.id = id;
	//	this.doctorId = doctorId;
		this.patientId = patientId;
		this.date = date;
		this.slot = slot;
	}
}
