package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateJobExperience;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.dtos.CandidateJobExperienceDto;

public interface CandidateJobExperienceService {
	
	DataResult<List<CandidateJobExperience>> getAll();
	DataResult<List<CandidateJobExperience>> getById(int id);
	DataResult<List<CandidateJobExperience>> orderedCandidateCvJobExperience(int id);
	Result add(CandidateJobExperienceDto jobExp);
	Result update(CandidateJobExperienceDto jobExp);
	Result delete(int id);
	DataResult<List<CandidateJobExperience>> findByCandidateCvId(int id);
	
}
