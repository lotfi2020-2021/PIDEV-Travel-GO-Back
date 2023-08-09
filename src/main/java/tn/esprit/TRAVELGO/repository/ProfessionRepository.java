package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Profession;
@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
}
