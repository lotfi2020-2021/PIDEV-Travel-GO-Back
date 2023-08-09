package tn.esprit.TRAVELGO.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Role;
import tn.esprit.TRAVELGO.entities.SexeType;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.message.ResponseMessage;

import java.util.List;

public interface IUserservice {
    ResponseEntity<ResponseMessage> saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username , String name);
    User getUser(String username);
    List<User>getUsers();
    List<User> retrieveUserBySexe(SexeType sexeUser);
    List<User> retrieveUserByAdress(String adressUser);
    User findByEmail(String Email);
    List<String> findByUserCompany(Long  id);


    /******************************************************WISSEM****************************************/
    public Business retrieveBusiness(Long id);
    public void Acceptation(Long id);
    public void Rejection(Long id);
    /******************************************************WISSEM****************************************/

}
