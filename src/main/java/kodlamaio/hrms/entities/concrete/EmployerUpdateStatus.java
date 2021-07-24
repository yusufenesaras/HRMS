package kodlamaio.hrms.entities.concrete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;

import kodlamaio.hrms.entities.dtos.EmployerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="employers_update_status")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
	  @TypeDef(name = "json", typeClass = JsonType.class),
	  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
	})

public class EmployerUpdateStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="employer_id")
	private int employerId;
	
	@Column(name = "json",columnDefinition = "json")
    @Type(type = "json")
    private EmployerDto update;
}
