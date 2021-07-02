package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateTalentService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateTalentDao;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;

@Service
public class CandidateTalentManager implements CandidateTalentService{
	
	private CandidateTalentDao candidateTalentDao;
	private DtoService dtoService;
	
	@Autowired
	public CandidateTalentManager(CandidateTalentDao candidateTalentDao,
			DtoService dtoService) {
		this.candidateTalentDao = candidateTalentDao;
		this.dtoService = dtoService;
		
	}
	
	@Override
	public DataResult<List<CandidateTalent>> getAll() {
		
		return new SuccessDataResult<List<CandidateTalent>>
		(this.candidateTalentDao.findAll(),"Başarılı Şekilde İŞ Arayanın Yetenekleri Listelendi");

	}

	@Override
	public Result add(CandidateTalentDto talent) {
		
		if(this.candidateTalentDao.existsCvIdAndTalentId(talent.getCvId(), talent.getTalentId()) != null) {
			return new ErrorResult("Daha önceden eklenmiş");
		}
		else {
			this.candidateTalentDao.save((CandidateTalent) this.dtoService.dtoClassConverter
					(talent, CandidateTalent.class));
			return new SuccessResult("başarılı");
		}
	}

	@Override
	public DataResult<List<CandidateTalent>> findByCandidateCvId(int id) {
		
        return new SuccessDataResult<List<CandidateTalent>>
        (this.candidateTalentDao.findByCandidateCvId(id),"Başarılı");

	}

	@Override
	public Result delete(int id) {
		this.candidateTalentDao.deleteById(id);
		return new SuccessResult("Talent Silindi.");
	}


	@Override
	public Result updateTalent(CandidateTalentDto candidateTalent) {
		
		CandidateTalent ref = this.candidateTalentDao.findById(candidateTalent.getId());
		
		ref.setId(candidateTalent.getId());
		ref.setTalentName(candidateTalent.getTalentName());
		
		 this.candidateTalentDao.save((CandidateTalent)
				 dtoService.dtoClassConverter(ref, CandidateTalent.class));
		
		return new SuccessResult("başarılı");	
	} 


}
