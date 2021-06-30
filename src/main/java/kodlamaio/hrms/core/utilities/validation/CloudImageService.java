package kodlamaio.hrms.core.utilities.validation;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface CloudImageService {
	
	@SuppressWarnings("rawtypes")
	DataResult<Map> upload(MultipartFile multipartFile) throws IOException;
	@SuppressWarnings("rawtypes")
	DataResult<Map> delete(String id) throws IOException;
	
}
