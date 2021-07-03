package kodlamaio.hrms.api.controllers;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Candidate;
import kodlamaio.hrms.entities.concrete.CandidateCv;
import kodlamaio.hrms.entities.dtos.CandidateCvDto;

@RestController
@RequestMapping("api/cv")
@CrossOrigin
public class CandidatesCvController {
	
private CandidateCvService candidateCvService;
	
	@Autowired
	public CandidatesCvController(CandidateCvService candidateCvService) {
		this.candidateCvService = candidateCvService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CandidateCv>> getAll(){
		return this.candidateCvService.getAll();
	}
	
	@GetMapping("/findbycandidateid")
	public DataResult<List<CandidateCv>> findByCandidateId(@RequestParam int id) {
		
		return this.candidateCvService.findByCandidateId(id);
	}
	
	@GetMapping("/findbycvid")
	public DataResult<CandidateCv> findByCvId(@RequestParam int id) {
		
		return this.candidateCvService.findById(id);
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CandidateCvDto candidateCv){
		return this.candidateCvService.add(candidateCv);
	}
	
	@PostMapping("/addcvphoto")
	public Result uploadCvPhoto(@RequestParam int candidateCvId, 
			@RequestParam MultipartFile multipartFile) throws IOException{
		return this.candidateCvService.uploadCvPhoto(candidateCvId, multipartFile);
	}
	@PostMapping("/updateCoverLetter")
	public Result updateCoverLetter(String text, int cvId) {
		return this.candidateCvService.updateCoverLetter(text, cvId);
	}
	
	// Dto ile güncelleme eklendi
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CandidateCvDto candidateCv){
		return ResponseEntity.ok(this.candidateCvService.update(candidateCv));
	}
	

}
