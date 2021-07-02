package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

public interface CandidateSchoolService {
	
	DataResult<List<CandidateSchool>> getAll();
	DataResult<List<CandidateSchool>> findByCandidateId(int id);
	DataResult<CandidateSchool> updateSchool(CandidateSchool candidateSchool);
	DataResult<List<CandidateSchool>> orderedCandidateCvSchools(int id);
	DataResult<List<CandidateSchool>> getBySchoolId(int id);
	Result updateSchool(CandidateSchoolDto candidateSchool);
	Result delete(int id);
	Result add(CandidateSchoolDto school);
	DataResult<List<CandidateSchool>> findByCandidateCvId(int id);
	
}
