package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.WorkHourService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkHourDao;
import kodlamaio.hrms.entities.concrete.WorkHour;

@Service
public class WorkHourManager implements WorkHourService{
	
	private WorkHourDao workHourDao;
	
	@Autowired
	public WorkHourManager(WorkHourDao workHourDao) {
		super();
		this.workHourDao = workHourDao;
	}
	
	@Override
	public DataResult<List<WorkHour>> getAll() {
		
		return new SuccessDataResult<List<WorkHour>>
		(this.workHourDao.findAll(),"Çalışma Saatleri Listelendi");
	}

}
