package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TalentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.TalentDao;
import kodlamaio.hrms.entities.concrete.Talent;

@Service
public class TalentManager implements TalentService{
	
	private TalentDao talentDao;

	@Autowired
	public TalentManager(TalentDao talentDao) {
		super();
		this.talentDao = talentDao;
	}
	
	@Override
	public DataResult<List<Talent>> getAll() {
		
		return new SuccessDataResult<List<Talent>>
		(this.talentDao.findAll(),"Yetenekler Listelendi");
	}

}
