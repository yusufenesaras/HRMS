package kodlamaio.hrms.core.utilities.validation;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.thirdPartyServices.FakeMernis;

public class IdentityValidation {
	
	public static Result isRealPerson(String tcno) {
		FakeMernis mernis = new FakeMernis();
		if(mernis.ValidateByPersonalInfo(tcno) == true) {
			return new SuccessResult();
		}
		return new ErrorResult("Türkiye Cumhuriyeti kimliği olmak zorunda.");
	}
}
