package tn.esprit.TRAVELGO.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.TRAVELGO.entities.UserDetails;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {


    Optional<UserDetails> findByUserId(Long id);

}
