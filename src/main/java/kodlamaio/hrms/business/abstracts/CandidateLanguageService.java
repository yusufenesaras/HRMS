package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;

public interface CandidateLanguageService {
	
	DataResult<List<CandidateLanguage>> getAll();
	Result add(CandidateLanguageDto lang);
	Result update(int cvId,int langId,int level);
	
	DataResult<List<CandidateLanguage>> findByCandidateCvId(int id);
}
