package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Profession;
import tn.esprit.TRAVELGO.entities.User;


import java.util.List;

public interface IProfessionService {
    Profession addprofession(Profession profession, User user);
    public List<Profession> getAll ();
}
