package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concrete.City;

public interface CityService {
	
	DataResult<City> add(City city);
	DataResult<List<City>> getAll();
	
}
