package kodlamaio.hrms.entities.concrete;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Varsayılan olarak, tüm statik olmayan, geçici olmayan alanları kullanır, 
//ancak tür üyelerini @EqualsAndHashCode ile işaretleyerek hangi alanların kullanıldığını değiştirebilir 
//(ve hatta çeşitli yöntemlerin çıktısının kullanılacağını belirtebilirsiniz).

@EqualsAndHashCode(callSuper=false) 
@Data
@Entity
@Table(name = "candidates")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement"})
public class Candidate extends User{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "identification_number")
	private String identificationNumber;
	
	@Column(name = "birth_date")
	private Date birthDate;  
	

	
	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Fav> favourites;
	
}
