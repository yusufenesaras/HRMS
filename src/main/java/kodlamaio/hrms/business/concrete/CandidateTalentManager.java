package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateTalentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateTalentDao;
import kodlamaio.hrms.entities.concrete.CandidateTalent;

@Service
public class CandidateTalentManager implements CandidateTalentService{
	
	private CandidateTalentDao candidateTalentDao;
	
	@Autowired
	public CandidateTalentManager(CandidateTalentDao candidateTalentDao) {
		this.candidateTalentDao = candidateTalentDao;
	}
	
	@Override
	public DataResult<List<CandidateTalent>> getAll() {
		
		return new SuccessDataResult<List<CandidateTalent>>
		(this.candidateTalentDao.findAll(),"Başarılı Şekilde İŞ Arayanın Yetenekleri Listelendi");

	}

}
