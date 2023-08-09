package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Salary;

import java.util.List;

public interface SalaryService {
public void increase(Long idUser);
public Salary addSalary(Salary s);
public void AffectSalary(Long idSal,Long idemp);
public void decrease(Long idUser);
public List<Salary> listAll();
}
