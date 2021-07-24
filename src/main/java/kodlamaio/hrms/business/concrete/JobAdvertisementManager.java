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
		return new SuccessResult("İş ilanı güncellendi.");
	}

	@Override
	public Result delete(JobAdvertisement jobAdvertisement) {
		
		this.jobAdvertisementDao.delete(jobAdvertisement);
		return new SuccessResult("İş ilanı silindi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActive() {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAllByIsActive(true),"Başarılı");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveSorted() {
		
		Sort sort = Sort.by(Sort.Direction.ASC,"applicationDeadline"); // yükselen ASC , Alçalan DESC
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(sort));
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id) {
		
		if(!this.employerDao.existsById(id)) {
			return new ErrorDataResult<List<JobAdvertisement>>("İş veren bulunamadı.");
		}
		else {
			return new SuccessDataResult <List<JobAdvertisement>>
			(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Başarılı.");
		}
	}

	@Override
	public DataResult<JobAdvertisement> jobAdvertisementDisabled(int id) {
		
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisement>("İş veren bulunamadı.");
		}
		JobAdvertisement ref =  this.jobAdvertisementDao.getOne(id);
		ref.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(ref),"İş ilanı Pasif.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(jobAdvertisementDao.findAll(),"Data listelendi.");
	}
	
	private Result findEmployer(JobAdvertisementDto jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployerId())) {
			return new ErrorResult("İş veren bulunamadı");
		}
		return new SuccessResult();
	}
	
	
	private Result findCity(JobAdvertisementDto jobAdvertisement) {
		if(!this.cityDao.existsById(jobAdvertisement.getCityId())) {
			return new ErrorResult("Şehir bulunamadı");
		}
		return new SuccessResult();
	}
	
	private Result descriptionNullChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return new ErrorResult("İş tanımı boş bırakılamaz.");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == null) {
			return new ErrorResult("Minimum maaş girilmek zorundadır.");
		}
		return new SuccessResult();
	}
	
	
	private Result ifMaxSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == null) {
			return new ErrorResult("Maksimum maaş girilmek zorundadır.");
		}
		return new SuccessResult();
	}
	
	private Result minSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == 0) {
			return new ErrorResult("Minimum maaş 0 verilemez.");
		}
		return new SuccessResult();
	}
	
	private Result maxSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == 0) {
			return new ErrorResult("Maksimum maaş 0 verilemez.");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryEqualsMaxSalary(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() >= jobAdvertisement.getMaxSalary()) {
			return new ErrorResult("Minimum maaş, maksimum maaşa eşit olamaz.");
		}
		return new SuccessResult();
	}
	
	private Result ifQuotaSmallerThanOne(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getQuota() < 1) {
			return new ErrorResult("Açık pozisyon sayısı 1 den küçük olamaz.");
		}
		return new SuccessResult();
	}
	
	private Result appealExpirationChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getAppealExpirationDate() == null) {
			return new ErrorResult("Son başvuru tarihi girilmek zorundadır");
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobAdvertisement>> getOneJobAds(int id) {
		return  new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getOneById(id),"İş İlani Detayı Geldi");
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
		(this.jobAdvertisementDao.getConfirmedJobAdvertisements(),"Onaylanmış İş İlanlari Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getWaitingJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.getWaitingJobAdvertisements(),"Bekleyen İş İlanlari Listelendi");
	}

	@Override
	public Result changeIsActiveByCandidate(int jobAdvertId) {
		JobAdvertisement jobAdvertIsActiveCandidate = this.jobAdvertisementDao.findById(jobAdvertId);
		jobAdvertIsActiveCandidate.setActive(!jobAdvertIsActiveCandidate.isActive());
		this.jobAdvertisementDao.save(jobAdvertIsActiveCandidate);
		return new SuccessResult("İş ilanının employer tarafından aktifliği değiştirildi");
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
		return new SuccessResult("İş ilanının admin tarafından aktifliği değiştirildi.");
	}


}
