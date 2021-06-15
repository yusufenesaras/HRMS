package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concrete.User;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;


@Service
public class UserManager implements UserService{
	
	private UserDao userDao;
	
	@Autowired //projeyi tarayıp userDao'ya karşılık gelen sınıfı enjekte ediyor.
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		
		return new SuccessDataResult<List<User>>
		(this.userDao.findAll(), "Data listelendi.");
	}

	@Override
	public User add(User user) {
		
		return userDao.save(user);
	}
	
}
