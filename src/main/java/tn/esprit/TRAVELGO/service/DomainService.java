package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Domain;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.DomainRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;


import java.util.List;
@Service
@Slf4j
public class DomainService implements  IdomainService {

	@Autowired
	DomainRepository dr ;
	@Autowired
	UserRepository ur ;
	@Override
	public Domain adddomain(Domain domain ,  User user) {


		User existingUser = ur.findByUsername(user.getUsername());
		User user11 = new User();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			user11= ur.findByUsername(username);
		} else {
			String username = principal.toString();
			user11= ur.findByUsername(username);
		}
		if(existingUser != null)
		{
			log.info("This email already exists!");
		}else {
			domain.setIdCompanyN(user11.getId());

		List<Domain> domains = (List<Domain>) dr.findAll();
		int i=0;
		for(Domain d : domains) {
			if(d.getTitle().equals(domain.getTitle())) {
				 i=1;

				 dr.save(d);
			}
		}
		if(i==0) {

			dr.save(domain);
		}
		}
		return domain;
		
	}




	@Override
	public void removeDomain(Long iddomain) {
		Domain domain = dr.findById(iddomain).get();
		dr.delete(domain);
		
	}

	@Override
	public Domain getdomainbyUser(Long idUser){
		User user = (User) ur.findById(idUser).get();
		return user.getDomain();	
}
	}
