package tn.esprit.TRAVELGO.service;

import org.springframework.mail.SimpleMailMessage;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.entities.Warn;
import tn.esprit.TRAVELGO.repository.BusinessRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.repository.WarnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;


@Service
public class WarnServiceImp implements WarnService{
	@Autowired
	UserRepository userrepository;
	@Autowired
	WarnRepository warnrepository;
	@Autowired
	BusinessRepository businessrepository;

	@Autowired
	EmailService emailService;
	
	@Override
	public void Warning(Long idUser,Warn w) {
		int nbrrejected=0;
		List<Business> Businesses=businessrepository.findAll();
		User u=userrepository.findById(idUser).get();
		for(Business bs :Businesses) {
		if( bs.isAcceptation()==false)
		{nbrrejected++;}
		}
		if(nbrrejected>=3) {
			{w.setText("you should be more serious");
			warnrepository.save(w);
			 u.setWarn(w);
			 userrepository.save(u);}

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(u.getEmail());
			mailMessage.setSubject("Warning");
			mailMessage.setFrom("ahmed.zarrai@esprit.tn");
			mailMessage.setText("you should be more serious :");
			emailService.sendEmail(mailMessage);

			
		}
	}
	@Override
	public Warn addWarn(Warn w) {
		w.setText("you should be more serious");
		warnrepository.save(w);
		return null;
	}
}
