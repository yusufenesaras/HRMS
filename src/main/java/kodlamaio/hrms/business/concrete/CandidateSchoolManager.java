package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateSchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolDao;
import kodlamaio.hrms.entities.concrete.CandidateSchool;

@Service
public class CandidateSchoolManager implements CandidateSchoolService{
	
	private CandidateSchoolDao CandidateSchoolDao;
	private CandidateCvDao candidateCvDao;
	
	@Autowired
	public CandidateSchoolManager(CandidateSchoolDao candidateSchoolDao,CandidateCvDao candidateCvDao) {
		super();
		this.CandidateSchoolDao = candidateSchoolDao;
		this.candidateCvDao = candidateCvDao;
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
		(this.CandidateSchoolDao.save(ref),"Update oldu");
	}

	@Override
	public DataResult<List<CandidateSchool>> orderedCandidateCvSchools(int id) {
		if(!this.candidateCvDao.existsById(id)) {
			return new ErrorDataResult<>("Cv bulunamadı");
		}
		return new SuccessDataResult<List<CandidateSchool>>
		(this.CandidateSchoolDao.getSchoolsOrderByGraduationDateDesc(id),
				"Başarılı Şekilde İş arayanın okul bilgileri listelendi");
	}

}
