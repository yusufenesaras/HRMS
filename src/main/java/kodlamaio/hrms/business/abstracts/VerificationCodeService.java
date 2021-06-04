package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concrete.EmailVerificationCode;

public interface VerificationCodeService {
	
	void generateCode(EmailVerificationCode code, Integer id);
	Result verify(String verificationCode, Integer id);
}
