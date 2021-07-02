package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateJobExperienceService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateJobExperienceDao;
import kodlamaio.hrms.entities.concrete.CandidateJobExperience;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.dtos.CandidateJobExperienceDto;

@Service
public class CandidateJobExperienceManager implements CandidateJobExperienceService{
	
	private CandidateJobExperienceDao candidateJobExperienceDao;
	private CandidateCvDao candidateCvDao;
	private DtoService dtoService;
	
	
	@Autowired
	public CandidateJobExperienceManager(CandidateJobExperienceDao candidateJobExperienceDao,
			CandidateCvDao candidateCvDao, DtoService dtoService) {
		super();
		this.candidateJobExperienceDao = candidateJobExperienceDao;
		this.candidateCvDao = candidateCvDao;
		this.dtoService = dtoService;
	}
	
	@Override
	public DataResult<List<CandidateJobExperience>> getAll() {
		
		return new SuccessDataResult<List<CandidateJobExperience>>
		(this.candidateJobExperienceDao.findAll(),"İş adayının deneyimleri listelendi");

	}

	@Override
	public DataResult<List<CandidateJobExperience>> orderedCandidateCvJobExperience(int id) {
		if(!this.candidateCvDao.existsById(id)) {
			return new ErrorDataResult<>("Cv bulunamadı");
		}
		return new SuccessDataResult<List<CandidateJobExperience>>
		(this.candidateJobExperienceDao.getJobExperienceOrderByExitDateDesc(id),
				"Başarılı Şekilde İş arayanın tecrübe bilgileri listelendi");
	}

	@Override
	public DataResult<List<CandidateJobExperience>> getById(int id) {
		return new SuccessDataResult<List<CandidateJobExperience>>
		(this.candidateJobExperienceDao.getById(id),"Başarılı");
	}

	@Override
	public Result add(CandidateJobExperienceDto jobExp) {
		this.candidateJobExperienceDao.save((CandidateJobExperience) 
				this.dtoService.dtoClassConverter(jobExp, CandidateJobExperience.class));
		return new SuccessResult("Başarılı");
	}

	@Override
	public Result update(CandidateJobExperienceDto jobExp) {
		
		CandidateJobExperience ref = this.candidateJobExperienceDao.findById(jobExp.getId());
		if(jobExp.getWorkplaceName() != null) {
			ref.setWorkplaceName(jobExp.getWorkplaceName());
		}
		
		if(jobExp.getEntryDate() != null) {
			ref.setEntryDate(jobExp.getEntryDate());
		}
		
		if(jobExp.getExitDate() != null) {
			ref.setExitDate(jobExp.getExitDate());
		}
		
		if(jobExp.getJobDetail() != null) {
			ref.setJobDetail(jobExp.getJobDetail());
		}
		
		
		this.candidateJobExperienceDao.save(ref);
		
		
		return new SuccessResult("id:"+ref.getWorkplaceName());
	}

	@Override
	public DataResult<List<CandidateJobExperience>> findByCandidateCvId(int id) {
		
        return new SuccessDataResult<List<CandidateJobExperience>>
        (this.candidateJobExperienceDao.findByCandidateCvId(id),"Başarılı");

	}

	@Override
	public Result delete(int id) {
		
		this.candidateJobExperienceDao.deleteById(id);
		return new SuccessResult("Silindi.");
	}
	
	
}
