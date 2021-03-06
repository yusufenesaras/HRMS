package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.validation.Injection;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concrete.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	
	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	
	@Qualifier("dtoService")
	private DtoService dtoService;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, EmployerDao employerDao, CityDao cityDao,
			DtoService dtoSerivce) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.dtoService = dtoSerivce;
	}

	@Override
	public Result add(JobAdvertisementDto jobAdvertisement) {
		//private metotlar Result
		Result inject = Injection.run(
				findEmployer(jobAdvertisement),findCity(jobAdvertisement),descriptionNullChecker(jobAdvertisement),
				ifMinSalaryNull(jobAdvertisement),ifMaxSalaryNull(jobAdvertisement),minSalaryChecker(jobAdvertisement),
				maxSalaryChecker(jobAdvertisement),ifMinSalaryEqualsMaxSalary(jobAdvertisement),ifQuotaSmallerThanOne(jobAdvertisement),
				appealExpirationChecker( jobAdvertisement));
		
		if(!inject.isSuccess()) {
			
			return new ErrorResult(inject.getMessage());
		}
		
		this.jobAdvertisementDao.save((JobAdvertisement) 
				dtoService.dtoClassConverter(jobAdvertisement, JobAdvertisement.class));
		return new SuccessResult("eklendi");
	}

	@Override
	public Result update(JobAdvertisement jobAdvertisement) {
		
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("???? ilan?? g??ncellendi.");
	}

	@Override
	public Result delete(JobAdvertisement jobAdvertisement) {
		
		this.jobAdvertisementDao.delete(jobAdvertisement);
		return new SuccessResult("???? ilan?? silindi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActive() {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAllByIsActive(true),"Ba??ar??l??");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveSorted() {
		
		Sort sort = Sort.by(Sort.Direction.ASC,"applicationDeadline"); // y??kselen ASC , Al??alan DESC
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(sort));
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id) {
		
		if(!this.employerDao.existsById(id)) {
			return new ErrorDataResult<List<JobAdvertisement>>("???? veren bulunamad??.");
		}
		else {
			return new SuccessDataResult <List<JobAdvertisement>>
			(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Ba??ar??l??.");
		}
	}

	@Override
	public DataResult<JobAdvertisement> jobAdvertisementDisabled(int id) {
		
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisement>("???? veren bulunamad??.");
		}
		JobAdvertisement ref =  this.jobAdvertisementDao.getOne(id);
		ref.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(ref),"???? ilan?? Pasif.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(jobAdvertisementDao.findAll(),"Data listelendi.");
	}
	
	private Result findEmployer(JobAdvertisementDto jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployerId())) {
			return new ErrorResult("???? veren bulunamad??");
		}
		return new SuccessResult();
	}
	
	
	private Result findCity(JobAdvertisementDto jobAdvertisement) {
		if(!this.cityDao.existsById(jobAdvertisement.getCityId())) {
			return new ErrorResult("??ehir bulunamad??");
		}
		return new SuccessResult();
	}
	
	private Result descriptionNullChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return new ErrorResult("???? tan??m?? bo?? b??rak??lamaz.");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == null) {
			return new ErrorResult("Minimum maa?? girilmek zorundad??r.");
		}
		return new SuccessResult();
	}
	
	
	private Result ifMaxSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == null) {
			return new ErrorResult("Maksimum maa?? girilmek zorundad??r.");
		}
		return new SuccessResult();
	}
	
	private Result minSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == 0) {
			return new ErrorResult("Minimum maa?? 0 verilemez.");
		}
		return new SuccessResult();
	}
	
	private Result maxSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == 0) {
			return new ErrorResult("Maksimum maa?? 0 verilemez.");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryEqualsMaxSalary(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() >= jobAdvertisement.getMaxSalary()) {
			return new ErrorResult("Minimum maa??, maksimum maa??a e??it olamaz.");
		}
		return new SuccessResult();
	}
	
	private Result ifQuotaSmallerThanOne(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getQuota() < 1) {
			return new ErrorResult("A????k pozisyon say??s?? 1 den k??????k olamaz.");
		}
		return new SuccessResult();
	}
	
	private Result appealExpirationChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getAppealExpirationDate() == null) {
			return new ErrorResult("Son ba??vuru tarihi girilmek zorundad??r");
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobAdvertisement>> getOneJobAds(int id) {
		return  new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getOneById(id),"???? ??lani Detay?? Geldi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisementsWithPageable(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getConfirmedJobAdvertisements(pageable));
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getConfirmedJobAdvertisements(),"Onaylanm???? ???? ??lanlari Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getWaitingJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getWaitingJobAdvertisements(),"Bekleyen ???? ??lanlari Listelendi");
	}

	@Override
	public Result changeIsActiveByCandidate(int jobAdvertId) {
		JobAdvertisement jobAdvertIsActiveCandidate = this.jobAdvertisementDao.findById(jobAdvertId);
		jobAdvertIsActiveCandidate.setActive(!jobAdvertIsActiveCandidate.isActive());
		this.jobAdvertisementDao.save(jobAdvertIsActiveCandidate);
		return new SuccessResult("???? ilan??n??n employer taraf??ndan aktifli??i de??i??tirildi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getEmployersActiveJobAdvertisement(int id) {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id));
	}

	@Override
	public Result getConfirmedJobAdvertisementsbyAdmin(int id) {
		
		JobAdvertisement jobAdvertIsActiveAdmin = this.jobAdvertisementDao.findById(id);
		jobAdvertIsActiveAdmin.setConfirmed(!jobAdvertIsActiveAdmin.isConfirmed());
		this.jobAdvertisementDao.save(jobAdvertIsActiveAdmin);
		return new SuccessResult("???? ilan??n??n admin taraf??ndan aktifli??i de??i??tirildi.");
	}


}
