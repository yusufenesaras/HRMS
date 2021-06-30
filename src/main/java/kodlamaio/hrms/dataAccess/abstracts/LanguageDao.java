package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.Language;

public interface LanguageDao extends JpaRepository<Language,Integer>{
	
	@Query(value="select * from candidates_lang where candidates_cv_id =:cvId and languages_id=:langId  ",nativeQuery=true)
	CandidateLanguage getByCandidateCvId(int cvId); 
}
