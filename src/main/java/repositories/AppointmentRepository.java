package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
	

}
