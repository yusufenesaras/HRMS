package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concrete.WorkHour;

public interface WorkHourDao extends JpaRepository<WorkHour, Integer>{
	
	
}
