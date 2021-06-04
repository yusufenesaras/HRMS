package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateTalentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.CandidateTalent;

@RestController
@RequestMapping("/api/candidatetalents")
public class CandidatesTalentController {
	
	private CandidateTalentService candidateTalentService;
	
	@Autowired
	public CandidatesTalentController(CandidateTalentService candidateTalentService) {
		this.candidateTalentService = candidateTalentService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CandidateTalent>> getAll(){
		return this.candidateTalentService.getAll();
	}
}
