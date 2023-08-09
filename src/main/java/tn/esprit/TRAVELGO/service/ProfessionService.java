package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Profession;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.ProfessionRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;


import java.util.List;

@Service
@Slf4j
public class ProfessionService implements IProfessionService{
    @Autowired
	ProfessionRepository pr;
    @Autowired
	UserRepository ur ;

    @Override
	public Profession addprofession(Profession profession, User user) {
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
			profession.setIdCompanyN(user11.getId());
		List<Profession> Professions = (List<Profession>) pr.findAll();
		int i=0;
		for(Profession d : Professions) {
			if(d.getTitle().equals(profession.getTitle())) {
				 i=1;

				 pr.save(d);
			}
		}
		if(i==0) {
			pr.save(profession);
		}
		}
		return profession;
		
	}

    @Override
    public List<Profession> getAll() {
        return (List<Profession>) pr.findAll();
    }
}
