package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.User;
import kodlamaio.hrms.entities.dtos.UserDto;


public interface UserService {
	
	DataResult<List<User>> getAll();
	//Result add(User user)
	User add(User user);
	User findById(int id);
}
