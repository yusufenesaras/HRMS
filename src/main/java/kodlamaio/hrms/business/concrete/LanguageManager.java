package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concrete.Language;
import kodlamaio.hrms.entities.dtos.LanguageDto;

@Service
public class LanguageManager implements LanguageService{
	
	private LanguageDao languageDao;
	private DtoService dtoService;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao, DtoService dtoService ) {
		super();
		this.languageDao = languageDao;
		this.dtoService = dtoService;
	}

	@Override
	public DataResult<List<Language>> getAll() {
		
		return new SuccessDataResult<List<Language>>
		(this.languageDao.findAll(),"Diller listelendi");
	}

	@Override
	public Result add(LanguageDto lang) {
		this.languageDao.save((Language)
				this.dtoService.dtoClassConverter(lang, Language.class));
		return new SuccessResult("Başarılı");
	}
	
	
}
