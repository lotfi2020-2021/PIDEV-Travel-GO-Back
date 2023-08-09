package tn.esprit.TRAVELGO.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.Liking_per_comments;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.CommentRepository;
import tn.esprit.TRAVELGO.repository.Liking_per_comments_Repository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ILiking_per_comments_ServiceImpl implements ILiking_per_comments_Service {
	
	
	@Autowired
	Liking_per_comments_Repository liking_per_comments_Repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	
	

	@Override
	public Boolean addLiking1(Liking_per_comments l, long idUser, long idComment) {
		
		if(isLikeExists1(idUser, idComment) == true) {
			return false;
		}
		
		else {
			User user =  userRepository.findById(idUser).get();

			Comment comment =  commentRepository.findById(idComment).get();
			
	
			
			l.setUsers(user);
			l.setComments(comment);
			LocalDateTime creationDate = LocalDateTime.now();
			l.setReactDate(creationDate);
			liking_per_comments_Repository.save(l);
			return true;
		}
	
	}

	@Override
	public void deleteLiking1(long id) {
		liking_per_comments_Repository.deleteById(id);
		
	}

	@Override
	public List<Liking_per_comments> getLikingsByCommentId(long id) {
		return liking_per_comments_Repository.getLikesByCommentId(id);
	}

	@Override
	public List<Liking_per_comments> getLikingsByUserId1(long id) {
		return liking_per_comments_Repository.getLikesByUserId1(id);
		
	}

	@Override
	public boolean isLikeExists1(long idUser, long idComment) {
int count = liking_per_comments_Repository.isLikeExists1(idComment, idUser);
		
		if(count == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int countLikingByComment(int id) {
		List<Liking_per_comments> likes = liking_per_comments_Repository.getLikesByCommentId(id);
		return likes.size();
	
	}
	
	
	
	
	
	
	
	

}
