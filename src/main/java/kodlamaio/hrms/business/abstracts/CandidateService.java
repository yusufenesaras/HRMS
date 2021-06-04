package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.Candidate;

public interface CandidateService {
	
	//Result add(Candidate candidate);


	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> add(Candidate candidate);
}
