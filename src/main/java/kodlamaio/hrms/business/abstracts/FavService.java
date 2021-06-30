package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Fav;

public interface FavService {
	
	DataResult<List<Fav>> getAll();
	DataResult<Fav> findByCandidateIdAndJobId(int id,int jobId);

}
