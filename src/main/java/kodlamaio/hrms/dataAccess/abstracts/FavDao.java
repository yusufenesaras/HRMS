package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.Fav;

public interface FavDao extends JpaRepository<Fav, Integer>{
	
	@Query(value="select * from favs where candidate_id =:id and job_advertisement_id=:jobId",nativeQuery=true)
	Fav findByCandidateIdAndJobId(int id,int jobId);

	@Modifying
	@Transactional
	@Query(value="delete from favs where candidate_id =:id and job_advertisement_id=:jobId ",nativeQuery=true)
	void deleteByCandidateIdAndJobId(int id, int jobId);

	@Query(value="select * from favs where candidate_id =:id ",nativeQuery=true)
	List<Fav> findByCandidateId(int id);
}
