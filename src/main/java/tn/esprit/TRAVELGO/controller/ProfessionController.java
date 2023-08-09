package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Domain;
import tn.esprit.TRAVELGO.entities.Profession;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.service.ProfessionService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfessionController {

    @Autowired
    ProfessionService ps;

    @PostMapping("/company/add-addP")
    public ResponseEntity<Profession> addProffession(@RequestBody Profession profession, User user){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
        return ResponseEntity.created(uri).body(ps.addprofession(profession,user));
    }
//c
    @GetMapping("/company/getallp")
    public List<Profession> getAll(){
        return ps.getAll();
    }
}
