package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Salary;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.BusinessRepository;
import tn.esprit.TRAVELGO.repository.SalaryRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalaryServiceImp implements SalaryService {
	@Autowired
	SalaryRepository salaryrepository;
	@Autowired
	BusinessRepository businessrepository;
	@Autowired
	UserRepository userrepository;
	@Override
	public void increase(Long idUser) {
		
		//Salary s=salaryrepository.findById(idSal).get();
		//Business bs=businessrepository.findById(idBus).get();
		int nbrAccepted=0;
		List<Business> Businesses=businessrepository.findAll();
		User u=userrepository.findById(idUser).get();
		for(Business bs :Businesses) {
		if( bs.isAcceptation())
		{nbrAccepted++;}
		}
		if(nbrAccepted>=3) {
			 u.getSalary().setIncrease(50);
			 userrepository.save(u);
			
		}
		}
	@Override
	public Salary addSalary(Salary s) {
		// TODO Auto-generated method stub
		return salaryrepository.save(s);
	}
	@Override
	public void AffectSalary(Long idSal, Long idemp) {
		User u=userrepository.findById(idemp).get();
		Salary s=salaryrepository.findById(idSal).get();
		u.setSalary(s);
		userrepository.save(u);
		
	}
	@Override
	public void decrease(Long idUser) {
		int nbrrejected=0;
		List<Business> Businesses=businessrepository.findAll();
		User u=userrepository.findById(idUser).get();
		for(Business bs :Businesses) {
		if( bs.isAcceptation()==false)
		{nbrrejected++;}
		}
		if(nbrrejected>=5) {
			{float amount = u.getSalary().getAmount();
			float reduction=(amount*5)/100;
			 u.getSalary().setReduction(reduction);
			 userrepository.save(u);}
		}
		
	}
	@Override
	public List<Salary> listAll() {
		List<Salary> salaries=salaryrepository.findAll();
		return salaries;
	}
			
		
	}
	


