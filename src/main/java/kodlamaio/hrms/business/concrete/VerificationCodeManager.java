package kodlamaio.hrms.business.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.validation.RandomCodeGenerate;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationDao;
import kodlamaio.hrms.entities.concrete.EmailVerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService{
	
	private EmailVerificationDao emailVerificationDao;
	
	@Autowired
	public VerificationCodeManager(EmailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
	}

	@Override
	public void generateCode(EmailVerificationCode code, Integer id) {
		//EmailVerificationCode _code = code;
		code.setCode(null);
		code.setVerified(false);
		if(code.isVerified() == false) {
			
			RandomCodeGenerate generator = new RandomCodeGenerate();
			String code_create = generator.create();
			code.setCode(code_create);
			code.setUserId(id);
	
			emailVerificationDao.save(code);
			
		}
		return ;
		
	}

	@Override
	public Result verify(String verificationCode, Integer id) {
		EmailVerificationCode code = this.emailVerificationDao.getOne(id);
		
		EmailVerificationCode ref = emailVerificationDao.findByUserId(id).stream().findFirst().get();
		if(ref.getCode().equals(verificationCode) && ref.isVerified() != true) {
			ref.setVerified(true);
			return  new SuccessDataResult<EmailVerificationCode>
			(this.emailVerificationDao.save(ref),"Başarılı");
		}
		else if(ref.isVerified() == true) {
			return  new ErrorDataResult<EmailVerificationCode>
			(null,"Zaten Doğrulanmış Hesap");
		}
		return  new ErrorDataResult<EmailVerificationCode>
		(null,"Doğrulama Kodu Geçersiz");

	}
	
}
