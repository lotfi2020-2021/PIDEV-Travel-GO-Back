package tn.esprit.TRAVELGO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.User;

import java.util.Optional;
@Repository
public interface Userrr extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
