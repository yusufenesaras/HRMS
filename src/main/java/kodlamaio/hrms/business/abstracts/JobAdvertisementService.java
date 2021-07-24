package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisementDto jobAdvertisement);
	Result update(JobAdvertisement jobAdvertisement);
	Result delete(JobAdvertisement jobAdvertisement);
	
	Result changeIsActiveByCandidate(int jobAdvertId);
	
	Result getConfirmedJobAdvertisementsbyAdmin(int id);
	
	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<List<JobAdvertisement>> findAllByIsActive();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveSorted();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id);
	
	DataResult<JobAdvertisement> jobAdvertisementDisabled(int id);
	
	DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisements();
	
	DataResult<List<JobAdvertisement>> getWaitingJobAdvertisements();
	
	DataResult<List<JobAdvertisement>> getOneJobAds(int id);
	
	DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisementsWithPageable(int pageNo, int pageSize);
	
	DataResult<List<JobAdvertisement>> getEmployersActiveJobAdvertisement(int id);

}
