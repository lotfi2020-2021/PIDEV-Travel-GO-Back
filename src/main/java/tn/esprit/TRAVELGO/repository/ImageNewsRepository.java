package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.ImageNews;

@Repository
public interface ImageNewsRepository extends CrudRepository <ImageNews,Integer> {

}
