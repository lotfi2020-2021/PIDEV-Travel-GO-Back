package tn.esprit.TRAVELGO.service;


import tn.esprit.TRAVELGO.entities.Domain;
import tn.esprit.TRAVELGO.entities.User;

public interface IdomainService {
	Domain adddomain(Domain domain , User user);
	public void removeDomain(Long iddomain);
	public Domain getdomainbyUser(Long idUser);
	
}
