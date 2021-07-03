package kodlamaio.hrms.business.concrete;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateCvService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.validation.CloudImageService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateJobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateLanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateTalentDao;
import kodlamaio.hrms.entities.concrete.CandidateCv;
import kodlamaio.hrms.entities.concrete.CandidateJobExperience;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.concrete.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateCvDto;

@Service
public class CandidateCvManager implements CandidateCvService{
	
	private CandidateCvDao candidateCvDao;
	private CandidateSchoolDao candidateSchoolDao;
	private CandidateTalentDao candidateTalentDao;
	private CandidateLanguageDao candidateLanguageDao;
	private CandidateJobExperienceDao candidateJobExperienceDao;
	private CloudImageService imageService;
	private DtoService dtoService;
	
	@Autowired
	public CandidateCvManager(CandidateCvDao candidateCvDao,CandidateSchoolDao candidateSchoolDao,
			CandidateTalentDao candidateTalentDao,CandidateLanguageDao candidateLanguageDao,
			CandidateJobExperienceDao candidateJobExperienceDao,CloudImageService imageService,DtoService dtoService) {
		
		this.candidateCvDao = candidateCvDao;
		this.candidateSchoolDao = candidateSchoolDao;
		this.candidateTalentDao = candidateTalentDao;
		this.candidateLanguageDao = candidateLanguageDao;
		this.candidateJobExperienceDao = candidateJobExperienceDao;
		this.imageService = imageService;
		this.dtoService = dtoService;
	}
	
	@Override
	public DataResult<List<CandidateCv>> getAll() {
		
		return new SuccessDataResult<List<CandidateCv>>
		(this.candidateCvDao.findAll(),"İş Arayan Cv'leri listelendi");
	}

	@Override
	public Result add(CandidateCvDto candidateCv) {
		candidateCv.setAvatarUrl("https://res.cloudinary.com/drtniio0r/image/upload/v1624707367/noperson_e8gskq.png");
		CandidateCv temporaryRef = candidateCvDao.save((CandidateCv) this.dtoService.dtoClassConverter(candidateCv, CandidateCv.class));	
		
		return new SuccessResult("İş Arayan Cv si eklendi");
	}

	@Override
	public DataResult<CandidateCv> findById(int id) {
		if(this.candidateCvDao.existsById(id)) {
			return new SuccessDataResult<CandidateCv>
			(this.candidateCvDao.findById(id),"İş Arayan Cv si listelendi");
		}
		return new ErrorDataResult<>("Cv bulunamadı");
	}
	

	@Override
	public Result uploadCvPhoto(int candidateCvId, MultipartFile multipartFile) throws IOException {
		
    var result = this.imageService.upload(multipartFile);
      var url = result.getData().get("url");
      
      CandidateCv ref = this.candidateCvDao.getOne(candidateCvId); 
      ref.setAvatarUrl(url.toString());
      this.candidateCvDao.save(ref);
      
        return new SuccessResult("başarılı");
	}


	@Override
	public DataResult<List<CandidateCv>> findByCandidateId(int id) {

		return new SuccessDataResult<List<CandidateCv>>(this.candidateCvDao.findByCandidateId(id));
	}
	

	private void setCvSchoolId(List<CandidateSchool> sc , CandidateCv cv) {
		for (CandidateSchool candidateSchool : sc) {
			candidateSchool.setCandidateCv(cv);
			candidateSchoolDao.save(candidateSchool);
		}
	}
	
	private void setCvTalentId(List<CandidateTalent> sc , CandidateCv cv) {
		for (CandidateTalent candidateTalent : sc) {
			candidateTalent.setCandidateCv(cv);
			candidateTalentDao.save(candidateTalent);
		}
	}
	
	private void setCvLanguageId(List<CandidateLanguage> sc , CandidateCv cv) {
		for (CandidateLanguage data : sc) {
			data.setCandidateCv(cv);
			candidateLanguageDao.save(data);
		}
	}
	
	private void setCvJobExperienceId(List<CandidateJobExperience> sc , CandidateCv cv) {
		for (CandidateJobExperience data : sc) {
			data.setCandidateCv(cv);
			candidateJobExperienceDao.save(data);
		}
	}

	@Override
	public Result updateCoverLetter(String text, int cvId) {
		CandidateCv ref = this.candidateCvDao.getOne(cvId);
		ref.setCoverLetter(text);
		this.candidateCvDao.save(ref);
		return new SuccessResult("Başarılı");
	}

	@Override
	public Result update(CandidateCvDto candidateCv) {
			
		CandidateCv ref = candidateCvDao.findById(candidateCv.getId());
		
		if(candidateCv.getAvatarUrl() != null) {
			ref.setAvatarUrl(candidateCv.getAvatarUrl());
		}
		 if(candidateCv.getCoverLetter() != null) {
			ref.setCoverLetter(candidateCv.getCoverLetter());
		}
		 if(candidateCv.getGithubAddress() != null) {
			ref.setGithubAddress(candidateCv.getGithubAddress());
		}
		 if(candidateCv.getLinkedinAddress() != null) {
			ref.setLinkedinAddress(candidateCv.getLinkedinAddress());
		}
		
		 this.candidateCvDao.save((CandidateCv)
				 dtoService.dtoClassConverter(ref, CandidateCv.class));
		
		return new SuccessResult("Başarılı");
	}

	
}
