package kodlamaio.hrms.business.concrete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateLanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.concrete.Language;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;

@Service
public class CandidateLanguageManager implements CandidateLanguageService{
	
	private CandidateLanguageDao candidateLanguageDao;
	private DtoService dtoService;
	private LanguageDao languageDao;
	
	@Autowired
	public CandidateLanguageManager(CandidateLanguageDao candidateLanguageDao,DtoService dtoService,
			LanguageDao languageDao) {
		super();
		this.candidateLanguageDao = candidateLanguageDao;
		this.dtoService = dtoService;
		this.languageDao = languageDao;
	}
	
	@Override
	public DataResult<List<CandidateLanguage>> getAll() {
		
		return new SuccessDataResult<List<CandidateLanguage>>
		(this.candidateLanguageDao.findAll(),"Başarılı");
	}

	@Override
	public Result add(CandidateLanguageDto lang) {
		
		if(this.candidateLanguageDao.existsCvIdAndLangId(lang.getCandidateCvId(), lang.getLanguageId()) != null) {
			return new ErrorResult("Daha önceden eklenmiş");
		}
		this.candidateLanguageDao.save((CandidateLanguage) dtoService.dtoClassConverter(lang, CandidateLanguage.class));
		return new SuccessResult("Başarılı");
	}

	@Override
	public Result update(int cvId, int langId, int level) {
		
		CandidateLanguage ref = this.candidateLanguageDao.getByCandidateCvIdAndLanguageId(cvId,langId);
		ref.setLevel(level);
		this.candidateLanguageDao.save(ref);
		return new SuccessResult(""+ref.getLevel());
	}

	@Override
	public DataResult<List<CandidateLanguage>> findByCandidateCvId(int id) {
		
		return new SuccessDataResult<List<CandidateLanguage>>
		(this.candidateLanguageDao.findByCandidateCvId(id),"Başarılı");
	}

	
}
