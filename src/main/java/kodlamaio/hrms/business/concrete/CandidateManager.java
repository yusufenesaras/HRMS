package kodlamaio.hrms.business.concrete;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.constants.FeedBack;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.validation.IdentityValidation;
import kodlamaio.hrms.core.utilities.validation.Injection;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concrete.Candidate;
import kodlamaio.hrms.entities.concrete.EmailVerificationCode;
import kodlamaio.hrms.entities.concrete.User;


@Service
public class CandidateManager implements CandidateService{
	
	
	private CandidateDao candidateDao;
	private VerificationCodeService verificationCodeService;
	private UserService userService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, VerificationCodeService verificationCodeService,
			UserService userService) {
		super();
		this.candidateDao = candidateDao;
		this.verificationCodeService = verificationCodeService;
		this.userService = userService;
	}

	
	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>
		(this.candidateDao.findAll(),FeedBack.listedCandidates);
	
	}

	
	@Override
	public DataResult<Candidate> add(Candidate candidate) {
		
		Result engine = Injection.run(firstNameChecker(candidate),lastNameChecker(candidate),
				IdentityValidation.isRealPerson(candidate.getIdentificationNumber()),
				IdChecker(candidate),birthDateChecker(candidate),emailNullChecker(candidate),
				isRealEmail(candidate),passwordNullChecker(candidate),isMailRegistered(candidate)
				);
		
		if(!engine.isSuccess()) {
			return new ErrorDataResult<Candidate>(null,engine.getMessage());
		}
		
		User savedUser = this.userService.add(candidate);
		this.verificationCodeService.generateCode(new EmailVerificationCode(),savedUser.getId());
		return new SuccessDataResult<Candidate>
		(this.candidateDao.save(candidate),FeedBack.isRegisterSuccessForCandidateMessage);
		
		
	}
	
	private Result firstNameChecker(Candidate candidate) {
		if(candidate.getFirstName().isBlank() || candidate.getFirstName().equals(null)) {
			return new ErrorResult(FeedBack.requiredFirstName);
			
		}
		return new SuccessResult();
	}
	
	private Result lastNameChecker(Candidate candidate) {
		if(candidate.getLastName().isBlank() || candidate.getLastName().equals(null)) {
			return new ErrorResult(FeedBack.requiredLastName);
		}
		return new SuccessResult();
	}
	
	private Result birthDateChecker(Candidate candidate) {
		if(candidate.getBirthDate().equals(null)) {
			return new ErrorResult(FeedBack.requiredBirthDate);
		}
		return new SuccessResult();
	}
	
	private Result emailNullChecker(Candidate candidate) {
		if(candidate.getEmail().isBlank() || candidate.getEmail().equals(null)) {
			return new ErrorResult(FeedBack.requiredEmail);
		}
		return new SuccessResult();
	}
	
	private Result passwordNullChecker(Candidate candidate) {
		if(candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return new ErrorResult(FeedBack.requiredPassword);
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(Candidate candidate) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(candidate.getEmail());
	     if(!matcher.matches()) {
	    		return new ErrorResult(FeedBack.isRealMail);
	     }
	     return new SuccessResult();
	     
	}
	
	private Result IdChecker(Candidate candidate) {
		if(candidate.getIdentificationNumber().isBlank()) {
			return new ErrorResult(FeedBack.requiredId);
		}
		
		 return new SuccessResult();
	}
	
	private Result isMailRegistered(Candidate candidate) {
		if(candidateDao.findByEmail(candidate.getEmail()).stream().count() != 0) {
			return new ErrorResult(FeedBack.alreadyRegisteredMail);
		}
		 return new SuccessResult();
	}
	
	@SuppressWarnings("unused")
	private Result isIdRegistered(Candidate candidate) {
		if(candidateDao.findAllByIdentificationNumber
				(candidate.getIdentificationNumber()).stream().count() != 0 ) {
			return new ErrorResult(FeedBack.alreadyRegisteredId);
		}
		 return new SuccessResult();
	}


	@Override
	public Result update(Candidate candidate) {
		this.candidateDao.save(candidate);
	      return new SuccessResult("Candidate güncellendi.");
	}


	@Override
	public DataResult<Candidate> getById(int id) {
		
		return new SuccessDataResult<Candidate>
		(this.candidateDao.getOne(id));

	}

	
}
