package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Employer;

public interface EmployerService {
	
	DataResult<Employer> add(Employer employer);
	DataResult<List<Employer>>getAll();
	
}
