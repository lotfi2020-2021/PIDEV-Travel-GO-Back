package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Domain;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.service.DomainService;

import java.net.URI;


@RestController
@RequestMapping("/api")
public class DomainController {
	@Autowired
	DomainService dr;


	@PostMapping("/company/add-addDomain")
	public ResponseEntity<Domain> adddomain(@RequestBody Domain domain, User user){
		URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
		return ResponseEntity.created(uri).body(dr.adddomain(domain,user));
	}
	
	//c
	@PostMapping("/company/remove/{idd}")
	@ResponseBody
	public void removeDomain(@PathVariable("idd")Long iddomain) {
		dr.removeDomain(iddomain);
	};
	
	//c
	@GetMapping("/company/getd/{idu}")
	@ResponseBody
	public Domain getdomainbyUser(@PathVariable("idu")Long idUser) {
		return dr.getdomainbyUser(idUser);
	};
}
