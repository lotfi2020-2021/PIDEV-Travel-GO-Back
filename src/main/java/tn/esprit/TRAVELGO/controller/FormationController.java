package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Formation;
import tn.esprit.TRAVELGO.service.FormationService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class FormationController {

    @Autowired
    FormationService fs;
//e
    @GetMapping("/users/addf")
    @ResponseBody
    public Formation addFormation(@RequestBody Formation formation){
        return fs.addFormation(formation);
    };
//e
    @PutMapping("/users/update/{idf}")
    @ResponseBody
    public void updateFormation(@PathVariable("idf") Long idf, @RequestBody Formation formation){
        fs.updateFormation(idf,formation);
    };
//e
    @DeleteMapping("/users/delete/{idf}")
    public void deleteFormation(@PathVariable("idf")Long idF){
        fs.deleteFormation(idF);
    };
//e
    @GetMapping("/users/Getbyuser/{idu}")
    public List<Formation> getFormationByUser(@PathVariable("idu") Long idUser){
        return fs.getFormationByUser(idUser);
    }
}
