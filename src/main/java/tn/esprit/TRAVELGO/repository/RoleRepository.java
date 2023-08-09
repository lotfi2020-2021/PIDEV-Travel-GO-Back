package tn.esprit.TRAVELGO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.ERole;
import tn.esprit.TRAVELGO.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    @Query("SELECT COUNT(name) FROM Role  WHERE name ='ROLE_COMPANY'")
    float getNumberCOMPANY();
    @Query("SELECT COUNT(name) FROM Role  WHERE name ='ROLE_USER'")
    float getNumberUser();
    Optional<Role> findByName(ERole name);
}
