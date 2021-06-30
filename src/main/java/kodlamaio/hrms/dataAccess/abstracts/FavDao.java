package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.Fav;

public interface FavDao extends JpaRepository<Fav, Integer>{
	
	List<Fav> findByCandidateId(int id);
	
	@Query(value="select * from favs where candidate_id =:id and job_advertisement_id=:jobId",nativeQuery=true)
	Fav findByCandidateIdAndJobId(int id,int jobId);
}
