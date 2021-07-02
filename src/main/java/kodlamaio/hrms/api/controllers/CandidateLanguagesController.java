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

import kodlamaio.hrms.business.abstracts.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.CandidateLanguage;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

@RestController
@RequestMapping("api/candidatelanguages")
@CrossOrigin
public class CandidateLanguagesController {
	
private CandidateLanguageService candidateLanguageService;
	
	@Autowired
	public CandidateLanguagesController(CandidateLanguageService candidateLanguageService) {
		super();
		this.candidateLanguageService = candidateLanguageService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CandidateLanguage>> getAll(){
		return this.candidateLanguageService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CandidateLanguageDto lang) {
		
		return this.candidateLanguageService.add(lang);
	}
	
	@PostMapping("delete")
	public Result delete (@RequestParam("id") int id) {
		return this.candidateLanguageService.delete(id);
	}
	
	@GetMapping("/findByCandidateCvId")
	public DataResult<List<CandidateLanguage>> findByCandidateId(@RequestParam int id) {
		
		return this.candidateLanguageService.findByCandidateCvId(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody CandidateLanguageDto lang) {
		
		return this.candidateLanguageService.updateLang(lang);
	}
	
}
