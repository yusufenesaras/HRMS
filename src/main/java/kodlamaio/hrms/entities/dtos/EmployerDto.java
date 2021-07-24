package kodlamaio.hrms.entities.dtos;

import kodlamaio.hrms.entities.concrete.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDto extends User{
	
	private int id;
	private String company_name;
	private String webAddress;
	private String phoneNumber;
}
