package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Fav;
import kodlamaio.hrms.entities.dtos.FavDto;

public interface FavService {
	
	DataResult<List<Fav>> getAll();
	
	DataResult<Fav> findByCandidateIdAndJobId(int id,int jobId);
	

	Result add(FavDto favdto); 
	
	Result delete(int id,int jobId);

	DataResult<List<Fav>> findByCandidateId(int id);
}
