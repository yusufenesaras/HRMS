package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.EmployerUpdateStatus;

public interface EmployerUpdateStatusService {
	
	DataResult<List<EmployerUpdateStatus>> getAll();
	
	DataResult<EmployerUpdateStatus> findByEmployerId(int id);
	
	Result confirm(int id);
	
	Result add(EmployerUpdateStatus employer);
}
