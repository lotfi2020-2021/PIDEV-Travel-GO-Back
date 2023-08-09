package tn.esprit.TRAVELGO.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class BusinessController {
	@Autowired
	BusinessService businessservice;
	@GetMapping("/company/retrieve-all-businesses")
	@ResponseBody
	public List<Business> RetrieveAll(){
		List<Business> busnisses= businessservice.retrieveAllBusiness();
		return busnisses;
	}
	@GetMapping("/company/retrieve-business/{business-id}")
	@ResponseBody
	public Business Retrieve(@PathVariable("business-id") Long businessId) {
		Business business=businessservice.retrieveBusiness(businessId);
		return business;
	}
	/*@PostMapping("/company/add-business")
	@ResponseBody
	public Business addbusiness(@RequestBody Business bs) {
		return businessservice.addBusiness(bs);
	}*/

	@PostMapping("/company/add-business")
	public ResponseEntity<Business> saveNews(@RequestBody Business b, User user){
		URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
		return ResponseEntity.created(uri).body(businessservice.addBusiness(b,user));
	}


	@DeleteMapping("/company/remove-business/{business-id}")
	@ResponseBody
	public void removeClient(@PathVariable("business-id") Long businessId) {
		businessservice.DeleteBusiness(businessId);
	}
	@PutMapping("/company/affect-business/{idbus}/{idemp}")
	@ResponseBody
	public void AffectBusiness(@PathVariable("idbus") Long idBus,@PathVariable("idemp") Long idUs) {
		businessservice.AffectEmployee(idBus, idUs);
	}
	@PutMapping("/company/modify-business")
	@ResponseBody
	public void modifyClient(@RequestBody Business bs) {
		businessservice.UpdateBusiness(bs);}
		@GetMapping("/company/nbrOfbusinessesAccepted")
		@ResponseBody
		public int NumberOfAccepted() {
		return	businessservice.NumberBusAccept();
		}
		@GetMapping("/company/nbrOfbusinessesRejected")
		@ResponseBody
		public int NumberOfRejected() {
		return	businessservice.NumberBusReject();
		}
		@GetMapping("/company/retrieve-businessesAccepted")
		@ResponseBody
		public List<Business> BusinessesAccepted(){
			List<Business> busnisses= businessservice.ListBusinessAccepted();
			return busnisses;
		}
		//////////////////////////////////////
		/*@GetMapping("/retrieve-businessByEmp/{emp-id}")
		@ResponseBody
		public List<Business> businessByEmplo(@PathVariable("emp-id") Long EmpId) {
			List<Business> busnisses= businessservice.ListBusinessByEmployee(EmpId);
			return busnisses;
		}*/
		//////////////////////////////////
		@GetMapping("/company/retrieve-businessesrejected")
		@ResponseBody
		public List<Business> BusinessesRejected(){
			List<Business> busnisses= businessservice.ListBusinessRejected();
			return busnisses;
		}
}

