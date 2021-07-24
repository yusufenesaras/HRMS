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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Employer;


@RestController
@RequestMapping("/api/employers")
@CrossOrigin

public class EmployersController {
	
	@Autowired
	private EmployerService employerService;

	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	@GetMapping("/getall")
	public DataResult <List<Employer>> getAll(){
		return this.employerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Employer employer){
		return this.employerService.add(employer);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Employer employer){
		return ResponseEntity.ok(this.employerService.update(employer));
	}
	
	@GetMapping("/getByIdForAdmins")
	public DataResult<Employer> getByIdForAdmins(int id){
		 return this.employerService.getByIdForAdmins(id);
	}
	
	@GetMapping("/getByIdForEmployers")
	public Employer getByIdForEmployers(int id){
		 return this.employerService.findByEmployerId(id);
	}
	
	@PostMapping("/changeverifiedstatus")
	public Result changeIsVerifiedByCandidate(@RequestParam int id) {
		return this.employerService.changeIsVerifiedByCandidate(id);
	}
	@GetMapping("/getAllByVerify")
	public DataResult<List<Employer>> getAllByVerify() {
		return this.employerService.getAllByVerify();
	}
	
	
}
