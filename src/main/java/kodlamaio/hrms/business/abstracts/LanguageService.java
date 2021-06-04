package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.Language;

public interface LanguageService {
	
	DataResult<List<Language>> getAll();
}
