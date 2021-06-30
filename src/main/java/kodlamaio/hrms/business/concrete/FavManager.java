package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.FavDao;
import kodlamaio.hrms.entities.concrete.Fav;

@Service
public class FavManager implements FavService{
	
	private FavDao favDao;
	
	@Autowired
	public FavManager(FavDao favDao) {
		super();
		this.favDao = favDao;
	}
	
	@Override
	public DataResult<List<Fav>> getAll() {
		return new SuccessDataResult<List<Fav>>
		(this.favDao.findAll(),"başarılı");
	}

	@Override
	public DataResult<Fav> findByCandidateIdAndJobId(int id, int jobId) {
		return new SuccessDataResult<Fav>
		(this.favDao.findByCandidateIdAndJobId(id,jobId),"başarılı");
	}

}
