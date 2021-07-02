package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;

public interface CandidateTalentService {
	
	DataResult<List<CandidateTalent>> getAll();
	Result add(CandidateTalentDto talent);
	Result delete(int id);
	Result updateTalent(CandidateTalentDto candidateTalent);
	DataResult<List<CandidateTalent>> findByCandidateCvId(int id);
	
	
}
