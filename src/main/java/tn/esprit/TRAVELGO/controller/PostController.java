package tn.esprit.TRAVELGO.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.model.FileDB;
import tn.esprit.TRAVELGO.repository.FileDBRepository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.PostServiceImpl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    
    @Autowired
    private PostRepository postRepository;
    
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FileDBRepository fileDBRepository;


    @GetMapping("/posts")
    public List<Post> getAllPosts( ) {
        return postRepository.findAll();
    }
    

 @PostMapping("/posts/{id-user}")
    public Post createPost(@Valid @RequestBody Post post, @PathVariable(value = "id-user") Long id) {
    	
    	User user = userRepository.findById(id).orElse(null);
    	post.setUsers(user);
  
        return postRepository.save(post);
    }
 
    @PostMapping("/posts/{id-user}/{file_id}")
    public Post createPost2(@Valid @RequestBody Post post, @PathVariable(value = "id-user") Long id,@PathVariable(value = "file_id") String fileid) {
    	
    	User user = userRepository.findById(id).orElse(null);
    	post.setUsers(user);
    	FileDB file = fileDBRepository.findById(fileid).orElse(null);
    	post.setFile(file);
  
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            post.setUpdatedAt(postRequest.getUpdatedAt());
            
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
    
    
    

	//http://localhost:8082/SpringMVC/servlet/post/search-post?pattern=evenement
	@GetMapping("/posts/{id}")
	public Post getPostById(@PathVariable("id")long idPost){
		
		
		return postService.getPostById(idPost);
	}
	
	@GetMapping("/Top2-posts")
	public String top2PostsByViews() {
		String res = "";
		for(Map.Entry map :postService.getPostsbyViewes().entrySet()) {
			res = res +"post numbero :"+map.getKey()+  " ----------  views :"+map.getValue()+"\n";

		}
		return res;
	} 
}
    
    
    


