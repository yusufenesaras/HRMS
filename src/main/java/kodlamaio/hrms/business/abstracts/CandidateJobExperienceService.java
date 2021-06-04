package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.CandidateJobExperience;

public interface CandidateJobExperienceService {
	
	DataResult<List<CandidateJobExperience>> getAll();
	DataResult<List<CandidateJobExperience>> orderedCandidateCvJobExperience(int id);
}
