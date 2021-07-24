package kodlamaio.hrms.business.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SystemUserService;
import kodlamaio.hrms.core.constants.FeedBack;
import kodlamaio.hrms.core.utilities.dtoTransfer.DtoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemUserDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concrete.CandidateCv;
import kodlamaio.hrms.entities.concrete.CandidateSchool;
import kodlamaio.hrms.entities.concrete.Employer;
import kodlamaio.hrms.entities.concrete.SystemUser;
import kodlamaio.hrms.entities.concrete.User;
import kodlamaio.hrms.entities.dtos.SystemUserDto;

@Service
public class SystemUserManager implements SystemUserService{
	
	private UserDao userDao;
	private SystemUserDao systemUserDao;
	private DtoService dtoService;
	
	@Autowired
	public SystemUserManager(SystemUserDao systemUserDao,UserDao userDao,DtoService dtoService) {
		super();
		this.systemUserDao = systemUserDao;
		this.userDao = userDao;
		this.dtoService = dtoService;
	}
	
	@Override
	public DataResult<List<SystemUserDto>> getAll() {
		
		return new SuccessDataResult<List<SystemUserDto>>
		(this.systemUserDao.getSystemUsers(),"Başarılı Şekilde Adminler Listelendi");
	}

	@Override
	public DataResult<SystemUser> add(SystemUser systemUser) {
		// Userın Id ye göre
		if(userDao.existsById(systemUser.getUser().getId())) {
			return new SuccessDataResult<SystemUser>(this.systemUserDao.save(systemUser),
					FeedBack.isRegisterSuccessForCandidateMessage);
		}
		return new ErrorDataResult<>("Kullanıcı Bulunamadı");
	}


	@Override
	public SystemUser findByUserId(int id) {
		
		return systemUserDao.findByUserId(id);
	}

	@Override
	public Result update(SystemUser systemUser) {
		
		this.systemUserDao.save(systemUser);
		return new SuccessResult("başarılı");
		
	}

	
}
