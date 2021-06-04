package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concrete.CandidateCv;

public interface CandidateCvDao extends JpaRepository<CandidateCv, Integer>{
	
	List<CandidateCv> findByCandidateId(int id);
	
}
