package tn.esprit.TRAVELGO.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.Abo_Repository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.AbonnementService;
import tn.esprit.TRAVELGO.service.PostServiceImpl;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
public class Abo_Controller {


	  @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private PostServiceImpl postService;
	    

	    @Autowired
	    private Abo_Repository AboRepository;
	    

	 
	    @GetMapping("/Follow")
	    public List<Abonnement> getAllPosts( ) {
	        return (List<Abonnement>) AboRepository.findAll();
	    }
	    
	    @GetMapping("/Follow/Posts/{id-user}")
	    public List<Post> getFaPosts (@PathVariable(value = "id-user") Long id1) {
	    	User user = userRepository.findById(id1).orElse(null);
			return postService.getFallowersPosts(user);
	    	
	        
	    }


	 
	    @PostMapping("/Follow/{id-user}/{id-user2}")
	    public Abonnement Subscribe(@Valid Abonnement abonnement, @PathVariable(value = "id-user") Long id1,@PathVariable(value = "id-user2") Long id2) {
	    	
	    	User user = userRepository.findById(id1).orElse(null);
	    	User user2 = userRepository.findById(id2).orElse(null);
	    	abonnement.setFollowed(user);
	    	abonnement.setFolowers(user2);
	    	abonnement.setIdUser(id2);
	  
	        return AboRepository.save(abonnement);
	    }

	   

	    @DeleteMapping("/Follow/{id-Abo}")
	    public ResponseEntity<?> deleteSub(@PathVariable(value = "id-Abo") Long id) {
	        return AboRepository.findById(id).map(abonnement -> {
	        	AboRepository.delete(abonnement);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Abonnemt id " + id + " not found"));
	    }
	    
	    @GetMapping("/Follow/User/{id-user}")
	    public List<User> abonnementByUser(@PathVariable(value = "id-user") Long id) {
	    	User user = userRepository.findById(id).orElse(null);
	    	
	        return  (List<User>) AboRepository.findFollowed(user);
	    }
	    
	    
	    
	    
	    


}