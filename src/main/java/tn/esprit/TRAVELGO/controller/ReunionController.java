package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Reunion;
import tn.esprit.TRAVELGO.service.ReunionService;


@RestController
    @RequestMapping("/api")
public class ReunionController {
    @Autowired
    ReunionService rs ;

    @GetMapping("/company/add")
    @ResponseBody
    //c
    public Reunion add(@RequestBody Reunion reunion){
        return rs.add(reunion);
    }
//c
    @PutMapping("/company/updater/{idr}")
    public Reunion updateReunion(@PathVariable("idr")Long idr,@RequestBody Reunion reunion){
        return rs.updateReunion(idr,reunion);
    }
//c
    @DeleteMapping("/company/deleter/{idr}")
    public void delete(@PathVariable("idr") Long idReunion){
        rs.delete(idReunion);
    }
    //c
    @PostMapping("/company/sendDomain/{id}/{idr}")
    @ResponseBody
    public void sendInvitationByDomain(@PathVariable("id") Long id,@PathVariable("idr") Long idR){
        rs.sendInvitationByDomain(id,idR);
    }//c
    @PostMapping("/company/sendbyProfession/{id}/{idr}")
    @ResponseBody
    public void sendInvtationByProfession(@PathVariable("id") Long id,@PathVariable("idr") Long idp){
        rs.sendInvtationByProfession(id,idp);
    }

}
