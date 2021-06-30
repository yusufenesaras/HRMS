package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concrete.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	List<Employer> findAllByEmail(String email);
	boolean existsById(int id);
	Employer getById(int id);	
}
