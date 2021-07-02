package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateTalentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;

@RestController
@RequestMapping("/api/candidatetalents")
@CrossOrigin
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
	
	@PostMapping("/add")
	public Result add(@RequestBody CandidateTalentDto talent){
		return this.candidateTalentService.add(talent);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody CandidateTalentDto lang) {
		
		return this.candidateTalentService.updateTalent(lang);
	}
	
	
	@PostMapping("delete")
	public Result delete(@RequestParam("id") int id){
		return this.candidateTalentService.delete(id);
	}
	
	@GetMapping("/findByCandidateCvId")
	public DataResult<List<CandidateTalent>> findByCandidateId(@RequestParam int id) {
		
		return this.candidateTalentService.findByCandidateCvId(id);
	}
}
