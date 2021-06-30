package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.Candidate;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidatesController {
	
	
	private CandidateService candidateService;

	public CandidatesController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Candidate>> getAll(){
		return this.candidateService.getAll();
	}
	
	//Requestbody istek yapıp datayı önizler.
	
	@PostMapping("/add")
	public DataResult<Candidate> add(@RequestBody Candidate candidate){
		return this.candidateService.add(candidate);
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Candidate candidate){
		return ResponseEntity.ok(this.candidateService.update(candidate));
	}
	
	@GetMapping("/getbyid")
	public DataResult<Candidate> getById(@RequestParam int id){
		return this.candidateService.getById(id);
	}
}
