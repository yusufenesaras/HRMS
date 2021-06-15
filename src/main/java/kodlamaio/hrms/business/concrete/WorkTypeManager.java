package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkTypeDao;
import kodlamaio.hrms.entities.concrete.WorkType;

@Service
public class WorkTypeManager implements WorkTypeService{
	
	private WorkTypeDao workTypeDao;

	@Autowired
	public WorkTypeManager(WorkTypeDao workTypeDao) {
		super();
		this.workTypeDao = workTypeDao;
	}
	
	@Override
	public DataResult<List<WorkType>> getAll() {
		
		return new SuccessDataResult<List<WorkType>>
		(this.workTypeDao.findAll(),"Çalışma Türleri Listelendi");
	}

}
