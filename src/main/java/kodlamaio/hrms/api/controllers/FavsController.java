package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.FavService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.Fav;
import kodlamaio.hrms.entities.dtos.FavDto;

@RestController
@RequestMapping("/api/favourite")
@CrossOrigin

public class FavsController {
	
	private FavService favService;
	
	
	public FavsController(FavService favService) {
		super();
		this.favService = favService;
	}

	@GetMapping("/getall")
	public DataResult<List<Fav>> getAll(){
		return this.favService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody FavDto favDto){
		return this.favService.add(favDto);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id,@RequestParam int jobId){
		return this.favService.delete(id,jobId);
	}
	@GetMapping("/findByCandidateId")
	public DataResult<List<Fav>> findByCandidateId(@RequestParam int id) {
		return this.favService.findByCandidateId(id);
	}
	
	@GetMapping("/findByCandidateIdAndJobId")
	public DataResult<Fav> findByCandidateIdAndJobId(@RequestParam int id,@RequestParam int jobId) {
		return this.favService.findByCandidateIdAndJobId(id,jobId);
	}
}
