package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	
	List<JobAdvertisement> getByEmployerId(int employerId);
	
	List<JobAdvertisement> findAllByIsActive(boolean isActive);
	
	List<JobAdvertisement> findAllByIsActiveOrderByCreatedDateDesc(boolean isActive);
	
	@Query("From JobAdvertisement where isActive = true and employer_id =:id")
	List<JobAdvertisement> getEmployersActiveJobAdvertisement(int id);

	boolean existsById(int id);
	
	List<JobAdvertisement> getOneById(int id);
}
