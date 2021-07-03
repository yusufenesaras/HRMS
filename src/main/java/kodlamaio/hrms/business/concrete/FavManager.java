package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.FavDao;
import kodlamaio.hrms.entities.concrete.Fav;
import kodlamaio.hrms.entities.dtos.FavDto;

@Service
public class FavManager implements FavService{
	
	private FavDao favDao;
	private DtoService dtoService;
	
	@Autowired
	public FavManager(FavDao favDao, DtoService dtoService) {
		super();
		this.favDao = favDao;
		this.dtoService = dtoService;
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

	@Override
	public Result add(FavDto favdto) {
		
		this.favDao.save((Fav) this.dtoService.dtoClassConverter(favdto, Fav.class));
		return new SuccessResult("Favorilere eklendi");
	}

	@Override
	public Result delete(int id, int jobId) {
		
		this.favDao.deleteByCandidateIdAndJobId(id,jobId);
		return new SuccessResult("başarılı");
	}

	@Override
	public DataResult<List<Fav>> findByCandidateId(int id) {
		
		return new SuccessDataResult<List<Fav>>
		(this.favDao.findByCandidateId(id),"başarılı");
	}


}
