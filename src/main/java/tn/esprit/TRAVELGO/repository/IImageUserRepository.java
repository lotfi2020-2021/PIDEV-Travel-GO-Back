package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.TRAVELGO.entities.ImageUser;

@Repository
public interface IImageUserRepository extends CrudRepository <ImageUser,Integer>{

}
