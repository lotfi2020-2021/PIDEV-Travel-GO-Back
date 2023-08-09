package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Liking_per_comments;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.service.ILiking_per_comments_ServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/liking")
public class Liking_per_Comments_Controller {
	@Autowired
	ILiking_per_comments_ServiceImpl likingService;
	
	@Autowired
	PostRepository postRepository;
	
	//http://localhost:8082/SpringMVC/servlet/post/add-posts/1
	@PostMapping("/add-like-comment/{id-user}/{id-comment}")
	public ResponseEntity<String> ajouterEtaffecterListeComments(@RequestBody Liking_per_comments like, @PathVariable(value = "id-user") Long id,
																 @PathVariable(value="id-comment")Long idComment)
	{
		if(likingService.addLiking1(like, id,idComment)) {
			return new ResponseEntity<String>("Nice your like is added :)",HttpStatus.OK);

		}
		else {
			return new ResponseEntity<String>("Sorry you already like this post  :)",HttpStatus.OK);

		}

	}

	//http://localhost:8082/SpringMVC/servlet/post/delete-post/3
	@DeleteMapping("/delete-like-comment/{id-like}")
	@ResponseBody
	public ResponseEntity<String> deleteLike(@PathVariable("id-like") Long id){
		likingService.deleteLiking1(id);
		return new ResponseEntity<String>("Like deleted with success   :)",HttpStatus.OK);
	}
	
	@GetMapping("like-comment/{id-comment}")
	public ResponseEntity<List<Liking_per_comments>> getLikingsByCommentId(@PathVariable(value = "id-comment")Long idComment) {
		if(!likingService.getLikingsByCommentId(idComment).isEmpty())
			
			return new ResponseEntity<List<Liking_per_comments>>(likingService.getLikingsByCommentId(idComment),HttpStatus.OK);
		else
			return null;
			
			

	}
	 
	@GetMapping("like-user-comment/{id-user}")
	public ResponseEntity<List<Liking_per_comments>>getLikesByUserId(@PathVariable(value = "id-user")Long idUser) {
		
	if(!likingService.getLikingsByCommentId(idUser).isEmpty())
			
			return new ResponseEntity<List<Liking_per_comments>>(likingService.getLikingsByUserId1(idUser),HttpStatus.OK);
		else
			return null;
				}
		

}
