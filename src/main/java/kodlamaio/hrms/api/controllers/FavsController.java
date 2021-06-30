package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.FavService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.Fav;

@RestController
@RequestMapping("/api/favourite")
@CrossOrigin

public class FavsController {
	
	private FavService favService;
	
	@Autowired
	public FavsController(FavService favService) {
		this.favService = favService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Fav>> getAll(){
		return this.favService.getAll();
	}
	
	@GetMapping("/findByCandidateId")
	public DataResult<Fav> findByCandidateId(@RequestParam int id,@RequestParam int jobId) {

		return this.favService.findByCandidateIdAndJobId(id,jobId);
	}
}
