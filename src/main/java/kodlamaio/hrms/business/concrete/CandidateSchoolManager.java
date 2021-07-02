package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateSchoolService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolDao;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

@Service
public class CandidateSchoolManager implements CandidateSchoolService{
	
	private CandidateSchoolDao CandidateSchoolDao;
	private CandidateCvDao candidateCvDao;
	private DtoService dtoService;
	
	@Autowired
	public CandidateSchoolManager(CandidateSchoolDao candidateSchoolDao,
			CandidateCvDao candidateCvDao,
			DtoService dtoService) {
		super();
		this.CandidateSchoolDao = candidateSchoolDao;
		this.candidateCvDao = candidateCvDao;
		this.dtoService = dtoService;
	}
	
	@Override
	public DataResult<List<CandidateSchool>> getAll() {
		
		return new SuccessDataResult<List<CandidateSchool>>
		(this.CandidateSchoolDao.findAll(),"İş Arayanın Eğitimi Listelendi");
	}

	@Override
	public DataResult<List<CandidateSchool>> findByCandidateId(int id) {
		
		return new SuccessDataResult<List<CandidateSchool>>
		(this.CandidateSchoolDao.findAll(),"İş Arayanın Eğitimi Listelendi");
	}

	@Override
	public DataResult<CandidateSchool> updateSchool(CandidateSchool candidateSchool) {
		
		CandidateSchool ref =  this.CandidateSchoolDao.findById(candidateSchool.getId());
		
		if(candidateSchool.getGraduationDate() != null) {
			ref.setGraduationDate(candidateSchool.getGraduationDate());
		}
		 if(candidateSchool.getEntryDate() != null) {
			ref.setEntryDate(candidateSchool.getEntryDate());
		}
		 if(candidateSchool.getSchoolName() != null) {
			ref.setSchoolName(candidateSchool.getSchoolName());
		}
		 if(candidateSchool.getDepartment() != null) {
			ref.setDepartment(candidateSchool.getDepartment());
		}
		
		return new SuccessDataResult<CandidateSchool>
		(this.CandidateSchoolDao.save(ref),"Update edildi.");
	}

	@Override
	public DataResult<List<CandidateSchool>> orderedCandidateCvSchools(int id) {
		if(!this.candidateCvDao.existsById(id)) {
			return new ErrorDataResult<>("Cv bulunamadı");
		}
		return new SuccessDataResult<List<CandidateSchool>>
		(this.CandidateSchoolDao.getSchoolsOrderByGraduationDateDesc(id),
				"İş arayanın okul bilgileri listelendi");
	}

	
	@Override
	public Result updateSchool(CandidateSchoolDto candidateSchool) {
		
		CandidateSchool ref =  this.CandidateSchoolDao.findById(candidateSchool.getId());
		
		if(candidateSchool.getGraduationDate() != null) {
			ref.setGraduationDate(candidateSchool.getGraduationDate());
		}
		 if(candidateSchool.getEntryDate() != null) {
			ref.setEntryDate(candidateSchool.getEntryDate());
		}
		 if(candidateSchool.getSchoolName() != null) {
			ref.setSchoolName(candidateSchool.getSchoolName());
		}
		 if(candidateSchool.getDepartment() != null) {
			ref.setDepartment(candidateSchool.getDepartment());
		}
		
		 this.CandidateSchoolDao.save((CandidateSchool)
				 dtoService.dtoClassConverter(ref, CandidateSchool.class));
		
		return new SuccessResult("başarılı");
	}

	@Override
	public Result add(CandidateSchoolDto school) {
		this.CandidateSchoolDao.save((CandidateSchool)
				dtoService.dtoClassConverter(school, CandidateSchool.class));
		return new SuccessResult("Başarılı");
	}

	@Override
	public DataResult<List<CandidateSchool>> getBySchoolId(int id) {
		
		return new SuccessDataResult<List<CandidateSchool>>
		(this.CandidateSchoolDao.getById(id),"Başarılı");
	}

	@Override
	public DataResult<List<CandidateSchool>> findByCandidateCvId(int id) {
		 return new SuccessDataResult<List<CandidateSchool>>
	        (this.CandidateSchoolDao.findByCandidateCvId(id),"Başarılı");
	}

	@Override
	public Result delete(int id) {
		
		this.CandidateSchoolDao.deleteById(id);
		return new SuccessResult("Okul silindi.");
	}

}
