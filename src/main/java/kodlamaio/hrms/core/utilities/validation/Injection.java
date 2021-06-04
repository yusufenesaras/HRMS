package kodlamaio.hrms.core.utilities.validation;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;


public class Injection { //Business Engine
	
	 public static Result run(Result... results) {
	        for (Result result : results) {
	            if (!result.isSuccess()){
	                return result;
	            }
	        }
	        return new SuccessResult();
	    }

	}

