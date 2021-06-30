package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Candidate;

public interface CandidateService {
	
	//Result add(Candidate candidate);
	Result update(Candidate candidate);
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> add(Candidate candidate);
	DataResult<Candidate> getById(int id);
}
