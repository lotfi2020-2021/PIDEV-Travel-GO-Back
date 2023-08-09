package tn.esprit.TRAVELGO.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.Liking_per_posts;
import tn.esprit.TRAVELGO.repository.Liking_per_posts_Repository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.service.ILiking_per_posts_ServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/liking")
public class Liking_per_Posts_Controller {
	@Autowired
	ILiking_per_posts_ServiceImpl likingService;
	@Autowired
	Liking_per_posts_Repository    likingRepo;
	@Autowired
	PostRepository postRepository;
	
	//http://localhost:8082/SpringMVC/servlet/post/add-posts/1
	@PostMapping("/add-like/{id-user}/{id-post}")
	public ResponseEntity<String> ajouterEtaffecterListePosts(@RequestBody Liking_per_posts like, @PathVariable(value = "id-user") Long id,
															  @PathVariable(value="id-post")Long idPost)
	{
		if(likingService.addLiking(like, id,idPost)) {
			return new ResponseEntity<String>("Nice your like is added :)",HttpStatus.OK);

		}
		else {
			return new ResponseEntity<String>("Sorry you already like this post  :)",HttpStatus.OK);

		}

	}
	
    @GetMapping("/allLiking")
    public List<Liking_per_posts> getAllLikings() {
        return  (List<Liking_per_posts>) likingRepo.findAll();
    }
    
	
	
	
	
	

	//http://localhost:8082/SpringMVC/servlet/post/delete-post/3
	@DeleteMapping("/delete-like/{id-like}")
	@ResponseBody
	public ResponseEntity<String> deleteLike(@PathVariable("id-like") Long id){
		likingService.deleteLiking(id);
		return new ResponseEntity<String>("Like deleted with success   :)",HttpStatus.OK);
	}
	
	@GetMapping("like-post/{id-post}")
	public ResponseEntity<List<Liking_per_posts>>getLikingsByPostId(@PathVariable(value = "id-post")Long idPost) {
		
			
			return new ResponseEntity<List<Liking_per_posts>>(likingService.getLikingsByPostId(idPost),HttpStatus.OK);
	
			
			

	}
	 
	@GetMapping("like-user/{id-user}")
	public ResponseEntity<List<Liking_per_posts>>getLikesByUserId(@PathVariable(value = "id-user")Long idUser) {
		

			
			return new ResponseEntity<List<Liking_per_posts>>(likingService.getLikingsByUserId(idUser),HttpStatus.OK);
		
		
	}
}
