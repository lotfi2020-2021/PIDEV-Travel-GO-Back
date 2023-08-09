package tn.esprit.TRAVELGO.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.CommentRepository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.BadWordFilter;
import tn.esprit.TRAVELGO.service.CommentService;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CommentController {

	 @Autowired
	    private CommentRepository commentRepository;

	    @Autowired
	    private PostRepository postRepository;
	    
	    @Autowired
	    private UserRepository userRepository;

	    @GetMapping("/posts/{postId}/comments")
	    public List<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId
	                                                ) {
	        return commentRepository.findByPostId(postId);
	    }

	    @PostMapping("/posts/{postId}/comments/{id-user}")
	    public Comment createComment(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Comment comment,@PathVariable(value = "id-user") Long id) {
	    	
	    	
	    	User user = userRepository.findById(id).orElse(null);
	    	comment.setUsers(user);
	  
	        return postRepository.findById(postId).map(post -> {
	            comment.setPost(post);
	       comment.setText(BadWordFilter.getCensoredText(comment.getText()).replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
	    	        .replaceAll("8", "gh").replaceAll("5", "kh").replaceAll("7", "h").replaceAll("0", "o").replaceAll("9", "k").replaceAll("2", "a"));
	       
	            return commentRepository.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	    }

	    @PutMapping("/posts/{postId}/comments/{commentId}")
	    public Comment updateComment(@PathVariable (value = "postId") Long postId,
	                                 @PathVariable (value = "commentId") Long commentId,
	                                 @Valid @RequestBody Comment commentRequest) {
	        if(!postRepository.existsById(postId)) {
	            throw new ResourceNotFoundException("PostId " + postId + " not found");
	        }

	        return commentRepository.findById(commentId).map(comment -> {
	            comment.setText(commentRequest.getText());
	           comment.setUpdatedAt(commentRequest.getUpdatedAt()) ;
	            return commentRepository.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
	    }

	    @DeleteMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
	                              @PathVariable (value = "commentId") Long commentId) {
	        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
	            commentRepository.delete(comment);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
	    }
	    
	    
	    
	    
	    @PostMapping("/posts/{postId}/comments/{parentId}/{id-user}")
	    public Comment addCommentToParentId(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Comment comment,@PathVariable(value = "id-user") Long id,@PathVariable (value = "parentId") Long commentId) {
	        Post post = postRepository.findById(postId)
	                .orElse(null);
	        
	    Comment parentComment = commentRepository.findById(commentId)
	                .orElse(null);

	        

	        User user = userRepository.findById(id)
	                .orElse(null);

	        comment.addComment(comment.getText());
	        comment.setParentComment(parentComment);
	        comment.setPost(post);
	        comment.setUsers(user);
	        return commentRepository.save(comment);
	    }
	    
	    
	    
	    
	    
	    
    
    
    
    
    
    
    
    
    
}