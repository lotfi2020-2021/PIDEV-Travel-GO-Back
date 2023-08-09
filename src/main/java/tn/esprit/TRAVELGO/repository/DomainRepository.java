package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Domain;

@Repository
public interface DomainRepository extends CrudRepository<Domain,Long> {
}
