package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Reunion;


@Repository
public interface RunionRepository extends CrudRepository<Reunion, Long> {
}
