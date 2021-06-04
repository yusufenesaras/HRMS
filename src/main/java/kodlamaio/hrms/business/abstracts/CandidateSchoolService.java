package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.CandidateSchool;

public interface CandidateSchoolService {
	
	DataResult<List<CandidateSchool>> getAll();
	DataResult<List<CandidateSchool>> findByCandidateId(int id);
	DataResult<CandidateSchool> updateSchool(CandidateSchool candidateSchool);
	DataResult<List<CandidateSchool>> orderedCandidateCvSchools(int id);
}
