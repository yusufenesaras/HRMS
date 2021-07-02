package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

public interface CandidateLanguageService {
	
	DataResult<List<CandidateLanguage>> getAll();
	Result add(CandidateLanguageDto lang);
	Result delete(int id);
	Result updateLang(CandidateLanguageDto lang);
	DataResult<List<CandidateLanguage>> findByCandidateCvId(int id);
	
}
