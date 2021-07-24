package kodlamaio.hrms.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SystemUserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.SystemUser;
import kodlamaio.hrms.entities.concrete.User;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;
import kodlamaio.hrms.entities.dtos.SystemUserDto;

@RestController
@RequestMapping("/api/systemUsers")
@CrossOrigin

public class SystemUsersController {
	
	private SystemUserService systemUserService;

	@Autowired
	public SystemUsersController(SystemUserService systemUserService) {
		super();
		this.systemUserService = systemUserService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<SystemUserDto>> getAll() {
		// TODO Auto-generated method stub
		return this.systemUserService.getAll();
	}
	
	@PostMapping("/add")
	public DataResult<SystemUser> add(@RequestBody SystemUser systemUser) {
		return this.systemUserService.add(systemUser);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody SystemUser systemUser) {
		return this.systemUserService.update(systemUser);
	}
	
	@GetMapping("/getByUserId")
	public SystemUser findByUserId(int id){
		 return this.systemUserService.findByUserId(id);
	}
}
