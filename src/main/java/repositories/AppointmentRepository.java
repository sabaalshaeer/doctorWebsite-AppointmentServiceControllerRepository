package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
	//write query that will find all appointments that has some matching PatientId/power of JPA
	//JPA will uses this exact name and convert it to SQL query
	//this query to return list or array of appointments , so we use Iterable class to denote this is going to be array,by PatientId with providing that id
	Iterable<Appointment> findAllByPatientId(Long id);
	

}
