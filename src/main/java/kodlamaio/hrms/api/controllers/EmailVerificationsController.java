package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/verification")

public class EmailVerificationsController {
	
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public EmailVerificationsController(VerificationCodeService verificationCodeService) {
		super();
		this.verificationCodeService = verificationCodeService;
	}
	
	@PostMapping("/update/{verificationCode}/{id}")
	
	public Result setVerify(@RequestParam String verificationCode,@RequestParam Integer id) {
			return verificationCodeService.verify(verificationCode,id);
	}
}
