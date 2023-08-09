package tn.esprit.TRAVELGO.repository;

import tn.esprit.TRAVELGO.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

}
