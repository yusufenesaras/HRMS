package kodlamaio.hrms.entities.concrete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="candidates_talent")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CandidateTalent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="talent_name")
	private String talentName;
	
    @ManyToOne(targetEntity = CandidateCv.class)
    @JoinColumn(name="candidates_cv_id")
    @JsonIgnore
    private CandidateCv candidateCv;
    
	@ManyToOne
	@JoinColumn(name = "talents_id")
	private Talent talent;
	
	
}
