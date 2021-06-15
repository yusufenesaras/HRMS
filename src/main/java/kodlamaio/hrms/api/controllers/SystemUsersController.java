package kodlamaio.hrms.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import kodlamaio.hrms.business.abstracts.SystemUserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.SystemUser;
import kodlamaio.hrms.entities.dtos.SystemUserDto;

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
}
