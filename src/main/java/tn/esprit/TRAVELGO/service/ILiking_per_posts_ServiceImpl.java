package tn.esprit.TRAVELGO.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Liking_per_posts;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ReactType;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.Liking_per_posts_Repository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;




@Service
public class ILiking_per_posts_ServiceImpl implements ILiking_per_posts_Service {
	
	@Autowired
	Liking_per_posts_Repository liking_per_posts_Repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	

	@Override
	public Boolean addLiking(Liking_per_posts l, long idUser, long idPost) {
		
		
		if(isLikeExists(idUser, idPost) == true) {
			return false;
		}
		
		else {
			User user =  userRepository.findById(idUser).get();

			Post post =  postRepository.findById(idPost).get();
			
			if(ReactType.spam.equals(l.getReactType())) {
				int spamNumber = 0;
				List<Liking_per_posts> spamReacts = post.getLikings().stream().filter(elem ->ReactType.spam.equals(elem.getReactType()) ).collect(Collectors.toList());
				if(spamReacts != null) {
					spamNumber = spamReacts.size();
				}
				if(spamNumber >= 2) {
					// traitement : delete post , block user
					postRepository.delete(post);
					return false;
				}
			}
			l.setUsers(user);
			l.setPosts(post);
			LocalDateTime creationDate = LocalDateTime.now();
			l.setReactDate(creationDate);
			liking_per_posts_Repository.save(l);
			
			
			return true;
			}
	}

	@Override
	public void deleteLiking(long id) {
		// TODO Auto-generated method stub
		liking_per_posts_Repository.deleteById(id);
		
	}

	@Override
	public List<Liking_per_posts> getLikingsByPostId(long id) {
		// TODO Auto-generated method stub
		return liking_per_posts_Repository.getLikesByPostId(id);
	}

	@Override
	public List<Liking_per_posts> getLikingsByUserId(long id) {
		return liking_per_posts_Repository.getLikesByUserId(id);
	}

	@Override
	public boolean isLikeExists(long idUser, long idPost) {
		int count = liking_per_posts_Repository.isLikeExists(idPost, idUser);
		
		if(count == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int countLikingByPost(int id) {
		List<Liking_per_posts> likes = liking_per_posts_Repository.getLikesByPostId(id);
		return likes.size();
	
	}
	

}
