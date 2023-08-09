package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.Abo_Repository;
import tn.esprit.TRAVELGO.repository.BusinessRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class AbonnementService implements IAbonnement {

    @Autowired
    Abo_Repository ar;
    @Autowired
    UserRepository userrepository;

    @Override
    public Abonnement addAbonnement(Abonnement a, User user, Long id) {
        User existingUser = userrepository.findByUsername(user.getUsername());
        User user11 = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user11 = userrepository.findByUsername(username);
        } else {
            String username = principal.toString();
            user11 = userrepository.findByUsername(username);
        }
        if (existingUser != null) {
            log.info("This email already exists!");
        } else {
            a.setIdUser(user11.getId());
            User user2 = userrepository.findById(id).orElse(null);
            a.setFollowed(user11);
            a.setFolowers(user2);
            ar.save(a);

        }
        return a;
    }
}
