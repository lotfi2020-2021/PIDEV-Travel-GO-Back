package tn.esprit.TRAVELGO.service;


import tn.esprit.TRAVELGO.entities.Formation;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;

public interface IFormationService {
     Formation addFormation(Formation formation);
    public void updateFormation(Long idf, Formation formation);
    public void deleteFormation(Long idF);
    public List<Formation> getFormationByUser(Long idUser);
}
