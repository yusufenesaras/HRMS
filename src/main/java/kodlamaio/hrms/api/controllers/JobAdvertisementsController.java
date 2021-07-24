package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;


@RestController
@RequestMapping("/api/jobAdvertisements")
@CrossOrigin

public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>>  getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getallactive")
	public DataResult<List<JobAdvertisement>>  getAllActive(){
		return this.jobAdvertisementService.findAllByIsActive();
	}

	
	@GetMapping("/getEmployerJobAdvertisement")
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id) {
		
		return this.jobAdvertisementService.findAllByIsActiveAndCompanyName(id);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisementDto jobAdvertisement){
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@PostMapping("/jobAdvertisementDisable")
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id) {
		return this.jobAdvertisementService.jobAdvertisementDisabled(id);
	}
	
	@GetMapping("/getOneById")
	public DataResult<List<JobAdvertisement>> getOneById(@RequestParam int id) {
		return this.jobAdvertisementService.getOneJobAds(id);
	}
	
	@GetMapping("/getConfirmedJobAdsWithPageable")
    public DataResult<List<JobAdvertisement>> getConfirmedJobAdsWithPageable(@RequestParam int pageNo, @RequestParam int pageSize) {
        return this.jobAdvertisementService.getConfirmedJobAdvertisementsWithPageable(pageNo,pageSize);
	}
	
	@GetMapping("/getWaitingJobAds")
	public DataResult<List<JobAdvertisement>> getWaitingJobAdvertisements() {
		return this.jobAdvertisementService.getWaitingJobAdvertisements();
	}
	
	@GetMapping("/getConfirmedJobAds")
	public DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisements() {
		return this.jobAdvertisementService.getConfirmedJobAdvertisements();
	}
	//onaylama
	@PostMapping("/changeactivestatus")
	public Result changeIsActiveByCandidate(@RequestParam int id) {
		return this.jobAdvertisementService.changeIsActiveByCandidate(id);
	}
	
	@PostMapping("/getEmployersActiveJobAdvertisement")
	public Result getEmployersActiveJobAdvertisement(@RequestParam int id) {
		return this.jobAdvertisementService.getEmployersActiveJobAdvertisement(id);
	}
	@PostMapping("/getConfirmedJobAdvertisementsbyAdmin")
	public Result getConfirmedJobAdvertisementsbyAdmin(@RequestParam int id) {
		return this.jobAdvertisementService.getConfirmedJobAdvertisementsbyAdmin(id);
	}
	
	
}