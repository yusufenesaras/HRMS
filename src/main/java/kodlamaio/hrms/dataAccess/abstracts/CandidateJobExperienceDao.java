package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.CandidateJobExperience;

public interface CandidateJobExperienceDao extends JpaRepository<CandidateJobExperience, Integer>{
	
	@Query("From CandidateJobExperience c where candidates_cv_id =:id ORDER BY exit_date, is_continue DESC")
	// terten sıralama 
	List<CandidateJobExperience> getJobExperienceOrderByExitDateDesc(int id);
}
