package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Formation;

@Repository
public interface FormationRepositry extends CrudRepository<Formation,Long> {
	
}
