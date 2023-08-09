package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;

public interface BusinessService {
 Business addBusiness(Business b, User user);
public List<Business> retrieveAllBusiness();
public Business retrieveBusiness(Long id);
public void DeleteBusiness(Long businessId);
public void UpdateBusiness(Business bs);
public void AffectEmployee(Long idBus,Long idUs);
public int NumberBusAccept();
public int NumberBusReject();
public List<Business> ListBusinessAccepted();
public List<Business>  ListBusinessRejected();
//public List<Business>  ListBusinessByEmployee(Long idUs);
}
