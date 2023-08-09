package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.BusinessRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BusinessServiceImp implements BusinessService {
@Autowired
BusinessRepository businessrepository;
@Autowired
UserRepository userrepository;
@Override
public Business addBusiness(Business b,User user) {
	User existingUser = userrepository.findByUsername(user.getUsername());
	User user11 = new User();
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
		String username = ((UserDetails)principal).getUsername();
		user11= userrepository.findByUsername(username);
	} else {
		String username = principal.toString();
		user11= userrepository.findByUsername(username);
	}
	if(existingUser != null)
	{
		log.info("This email already exists!");
	}else {
		b.setIdCompanyN(user11.getId());
		businessrepository.save(b);
		log.info("saving new news");
	}
	return b;
}

@Override
public List<Business> retrieveAllBusiness() {
	List<Business> businesses= businessrepository.findAll();
	return businesses;
}

@Override
public Business retrieveBusiness(Long id) {
	 Business business=businessrepository.findById(id).get();
	return  business;
}

@Override
public void DeleteBusiness(Long businessId) {
	businessrepository.deleteById(businessId);
	
}

@Override
public void UpdateBusiness(Business bs) {

	businessrepository.save(bs);
	
}

@Override
public void AffectEmployee(Long idBus, Long idUs) {
	Business b=	businessrepository.findById(idBus).get();
	User u=userrepository.findById(idUs).get();
	
	b.setUser(u);
	businessrepository.save(b);
}

@Override
public int NumberBusAccept() {
	int nbraccept=0;
	List<Business> busaccept= businessrepository.findAll();
	for(Business bs :busaccept) {
		if( bs.isAcceptation())
		{nbraccept++;}
		}
	return nbraccept;
}

@Override
public int NumberBusReject() {
	int nbrreject=0;
	List<Business> busreject= businessrepository.findAll();
	for(Business bs :busreject) {
		if( bs.isAcceptation()==false)
		{nbrreject++;}
		}
	return nbrreject;
}

@Override
public List<Business> ListBusinessAccepted() {
	List<Business> BusAccepted = new ArrayList() ;
	List<Business> businesses= businessrepository.findAll();
	for(Business bs :businesses) {
		if( bs.isAcceptation())
			BusAccepted.add(bs);
		}
	return BusAccepted;
}

@Override
public List<Business> ListBusinessRejected() {
	List<Business> BusReject = new ArrayList() ;
	List<Business> businesses= businessrepository.findAll();
	for(Business bs :businesses) {
		if( bs.isAcceptation()==false)
			BusReject.add(bs);
		}
	return BusReject;
}

/*@Override
public List<Business> ListBusinessByEmployee(Long idUs) {
	List<Business> busOfEmp= businessrepository.findByEmpId(idUs);
	return busOfEmp;
}*/


}
