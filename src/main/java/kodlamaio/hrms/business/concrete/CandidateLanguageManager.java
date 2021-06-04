package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateLanguageDao;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;

@Service
public class CandidateLanguageManager implements CandidateLanguageService{
	
	private CandidateLanguageDao candidateLanguageDao;
	
	@Autowired
	public CandidateLanguageManager(CandidateLanguageDao candidateLanguageDao) {
		super();
		this.candidateLanguageDao = candidateLanguageDao;
	}
	
	@Override
	public DataResult<List<CandidateLanguage>> getAll() {
		
		return new SuccessDataResult<List<CandidateLanguage>>
		(this.candidateLanguageDao.findAll(),"Başarılı");
	}
	
	
}
