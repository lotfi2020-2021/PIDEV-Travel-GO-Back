package tn.esprit.TRAVELGO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.SexeType;
import tn.esprit.TRAVELGO.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findBySexeUser(SexeType sexeUser);
    List<User> findByAdressUser(String adressUser);
    User findByEmail(String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    @Query("SELECT COUNT(sexeUser) FROM User  WHERE sexeUser ='Women'")
    float getNumberWomen();
    @Query("SELECT COUNT(sexeUser) FROM User  WHERE sexeUser ='Men'")
    float getNumberMen();
    @Query(value=" SELECT u.username from User  u where u.idCompany= :id ")
    List<String> findByUserCompany (@Param("id") Long id);

    
    User findUserById(Long id);
	



}
