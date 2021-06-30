package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concrete.CandidateCv;
import kodlamaio.hrms.entities.dtos.CandidateCvDto;

public interface CandidateCvDao extends JpaRepository<CandidateCv, Integer>{
	
	boolean existsById(int id);
	List<CandidateCv> findByCandidateId(int id);
	CandidateCv findById(int id);
	
	
}
