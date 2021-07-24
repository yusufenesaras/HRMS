package kodlamaio.hrms.business.concrete;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.validation.Injection;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concrete.EmailVerificationCode;
import kodlamaio.hrms.entities.concrete.Employer;
import kodlamaio.hrms.entities.concrete.User;

@Service
public class EmployerManager implements EmployerService{
	
	@Autowired
	private EmployerDao employerDao;
	private VerificationCodeService verificationCodeService;
	private UserService userService;
	
	public EmployerManager(EmployerDao employerDao, VerificationCodeService verificationCodeService,
			UserService userService) {
		super();
		this.employerDao = employerDao;
		this.verificationCodeService = verificationCodeService;
		this.userService = userService;
	}
	
	@Override
	public DataResult<Employer> add(Employer employer) {
		
		Result engine = Injection.run(
				companyNameChecker(employer),webSiteChecker(employer),passwordNullChecker(employer),
				isRealEmployer(employer),isRealPhoneNumber(employer),isEmailAlreadyRegistered(employer));
		
		if(!engine.isSuccess()) {
			return new ErrorDataResult<Employer>(null,engine.getMessage());
		}
		
		User savedUser = this.userService.add(employer);
		this.verificationCodeService.generateCode(new EmailVerificationCode(),savedUser.getId());
		return new SuccessDataResult<Employer>(this.employerDao.save(employer),
				"İş Veren Hesabı Eklendi, Doğrulama Kodu Gönderildi ID:"+employer.getId());
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(),"Employer listelendi.");
	}

	private Result companyNameChecker(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.getCompanyName() == null) {
			return new ErrorResult("Şirket Adı Doldurulmak Zorundadır");
		}
		return new SuccessResult();
	}
	
	private Result webSiteChecker(Employer employer) {
		if(employer.getWebAdress().isBlank() || employer.getWebAdress() == null) {
			return new ErrorResult("WebSite Adresi Doldurulmak Zorundadır");
		}
		return new SuccessResult();
	}
	
	private Result isRealEmployer(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Geçersiz Email Adresi");
	     }
	    else if(!employer.getEmail().contains(employer.getWebAdress())) {
	    	 return new ErrorResult("Domain adresi girmek zorundasınız"); 
	     } 
	 	return new SuccessResult();
	     
	}
	
	private Result isEmailAlreadyRegistered(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			 return new ErrorResult("Email zaten kayıtlı"); 
		}
	 	return new SuccessResult();
	}
	
	private Result passwordNullChecker(Employer employer) {
		if(employer.getPassword().isBlank() || employer.getPassword() == null) {
			 return new ErrorResult("Şifre Bilgisi Doldurulmak zorundadır"); 
		}
		return new SuccessResult();
	}
	
	private Result isRealPhoneNumber(Employer employer) {
		String patterns 
	      = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		
		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if(!matcher.matches()) {
			 return new ErrorResult("Geçersiz Telefon Numarası"); 
		}
		return new SuccessResult();
		
	}

	@Override
	public Result update(Employer employer) {
		
		this.employerDao.save(employer);
	      return new SuccessResult("Employer güncellendi.");
	}

	@Override
	public DataResult<Employer> getByIdForAdmins(int id) {
		return new SuccessDataResult<Employer>
		(this.employerDao.getById(id));
	}

	@Override
	public Result changeIsVerifiedByCandidate(int employerId) {
		Employer employer = this.employerDao.getById(employerId);
		employer.setVerified(!employer.isVerified());
		this.employerDao.save(employer);
		return new SuccessResult("Şirketin Doğrulanma durumu değiştirildi");
	}

	@Override
	public Employer findByEmployerId(int id) {
		return this.employerDao.getById(id);
	}

	@Override
	public DataResult<List<Employer>> getAllByVerify() {
		
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.getAllByVerify());
	}

	
}
