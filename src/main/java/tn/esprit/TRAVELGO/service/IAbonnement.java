package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.User;

public interface IAbonnement {
    Abonnement addAbonnement(Abonnement a, User user, Long id);
}
