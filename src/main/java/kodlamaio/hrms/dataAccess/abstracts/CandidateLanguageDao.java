package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.CandidateLanguage;

public interface CandidateLanguageDao extends JpaRepository<CandidateLanguage, Integer>{
	
	@Query(value="select * from candidates_lang where candidates_cv_id =:cvId and languages_id=:langId  ",nativeQuery=true)
	CandidateLanguage getByCandidateCvIdAndLanguageId(int cvId, int langId);
	
	@Query(value="select * from candidates_lang where candidates_cv_id =:cvId and languages_id=:langId LIMIT 1 ",nativeQuery=true)
	CandidateLanguage existsCvIdAndLangId(int cvId, int langId);
	
	@Query(value="select * from candidates_lang where candidates_cv_id =:cvId LIMIT 1 ",nativeQuery=true)
	CandidateLanguage getByCvId(int cvId);
	
	List<CandidateLanguage> findByCandidateCvId(int id);
	
	CandidateLanguage getById(int id);
	CandidateLanguage findById(int id);

}
