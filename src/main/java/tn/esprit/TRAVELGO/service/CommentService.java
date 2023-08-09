package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.model.FileDB;
import tn.esprit.TRAVELGO.repository.CommentRepository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

@Service
@Slf4j
public class CommentService implements IcommentService{


    @Autowired
    UserRepository ur ;
    @Autowired
    CommentRepository cr ;
    @Autowired
    PostRepository pr ;

    @Override
    public Comment addCommentt(Comment c, User user, Long id) {
        User existingUser = ur.findByUsername(user.getUsername());
        User user11 = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user11= ur.findByUsername(username);
        } else {
            String username = principal.toString();
            user11= ur.findByUsername(username);
        }
        if(existingUser != null)
        {
            log.info("This email already exists!");
        }else {
            c.setIdUser(user11.getId());
            return pr.findById(id).map(post -> {
                c.setPost(post);
                c.setText(BadWordFilter.getCensoredText(c.getText()).replaceAll("1",
                        "i").replaceAll("!",
                        "i").replaceAll("3",
                        "e").replaceAll("4",
                        "a").replaceAll("8",
                        "gh").replaceAll("5",
                        "kh").replaceAll("7",
                        "h").replaceAll("0",
                        "o").replaceAll("9",
                        "k").replaceAll("2",
                        "a"));
               return cr.save(c);
            }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
        }
        return c;
    }

    @Override
    public Comment addCommentt2(Comment c, User user, Long id, Long idc) {
        User existingUser = ur.findByUsername(user.getUsername());
        User user11 = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user11= ur.findByUsername(username);
        } else {
            String username = principal.toString();
            user11= ur.findByUsername(username);
        }
        if(existingUser != null)
        {
            log.info("This email already exists!");
        }else {
            c.setIdUser(user11.getId());
            Post post = pr.findById(id)
                    .orElse(null);
            Comment parentComment = cr.findById(idc)
                    .orElse(null);
            c.addComment(c.getText());
            c.setParentComment(parentComment);
            c.setPost(post);
            c.setUsers(user11);
    }
        return c;
}
}

