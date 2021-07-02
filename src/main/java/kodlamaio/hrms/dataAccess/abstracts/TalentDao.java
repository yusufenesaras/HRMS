package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concrete.Talent;

public interface TalentDao extends JpaRepository<Talent, Integer>{
	
	Talent getById(int id);
	
}
