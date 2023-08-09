package tn.esprit.TRAVELGO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Formation;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.FormationRepositry;
import tn.esprit.TRAVELGO.repository.UserRepository;


import java.util.List;

@Service
public class FormationService implements IFormationService{

    @Autowired
    FormationRepositry fr ;
    @Autowired
    UserRepository ur ;
    @Override


    public Formation addFormation(Formation formation) {
       return fr.save(formation);

    }

    @Override
    public void updateFormation(Long idf, Formation formation) {
    Formation formation1 = fr.findById(idf).get();
    formation1.setDatediplome(formation.getDatediplome());
    formation1.setDescription(formation.getDescription());
    formation1.setTitleDiplome(formation.getTitleDiplome());
    fr.save(formation1);
    }

    @Override
    public void deleteFormation(Long idf) {
        Formation formation1 = fr.findById(idf).get();
        fr.delete(formation1);
    }

    @Override
    public List<Formation> getFormationByUser(Long idUser) {
        User user = ur.findById(idUser).get();
        return user.getFormations();
    }
}
